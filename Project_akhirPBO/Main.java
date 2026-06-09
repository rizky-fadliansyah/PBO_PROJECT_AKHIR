import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Vector;

public class Main extends JFrame {
    private final UlasanService service = new UlasanService();
    private int nextId = 1;
    private final DefaultTableModel tableModel;

    public Main() {
        super("Aplikasi Ulasan Pembeli");

        Vector<String> columns = new Vector<>();
        columns.add("ID");
        columns.add("Produk");
        columns.add("Komentar");
        columns.add("Rating");
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);

        JCheckBox chkVIP = new JCheckBox("Member VIP");
        JButton btnFilterVIP = new JButton("Filter Ulasan VIP");

        JPanel formPanel = new JPanel(new GridLayout(12, 2, 10, 10));

        formPanel.add(new JLabel("Nama Pembeli:"));
        JTextField pembeliField = new JTextField();
        formPanel.add(pembeliField);

        formPanel.add(new JLabel("Nama Toko/Penjual:"));
        JTextField penjualField = new JTextField();
        formPanel.add(penjualField);

        formPanel.add(new JLabel("ID Ulasan berikutnya:"));
        JTextField nextIdField = new JTextField(String.valueOf(nextId));
        nextIdField.setEditable(false);
        formPanel.add(nextIdField);

        formPanel.add(new JLabel("ID Ulasan (Update/Hapus):"));
        JTextField idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Nama Produk:"));
        JTextField produkField = new JTextField();
        formPanel.add(produkField);

        formPanel.add(new JLabel("Rating [1-5]:"));
        JTextField ratingField = new JTextField();
        formPanel.add(ratingField);

        formPanel.add(new JLabel("Komentar:"));
        JTextField komentarField = new JTextField();
        formPanel.add(komentarField);

        formPanel.add(chkVIP);
        formPanel.add(btnFilterVIP);
        JButton addButton = new JButton("Tambah Ulasan");
        JButton updateButton = new JButton("Update Ulasan");
        JButton deleteButton = new JButton("Hapus Ulasan");
        JButton clearButton = new JButton("Bersihkan Form");
        formPanel.add(addButton);
        formPanel.add(updateButton);
        formPanel.add(deleteButton);
        formPanel.add(clearButton);

        JButton refreshButton = new JButton("Refresh Daftar");
        formPanel.add(refreshButton);
        formPanel.add(new JLabel());

        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.WEST);
        add(tableScroll, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            try {
                String namaPembeli = pembeliField.getText().trim();
                String namaPenjual = penjualField.getText().trim();
                String produk = produkField.getText().trim();
                String komentar = komentarField.getText().trim();
                String ratingText = ratingField.getText().trim();

                if (namaPembeli.isEmpty() || namaPenjual.isEmpty() || produk.isEmpty() || komentar.isEmpty()
                        || ratingText.isEmpty()) {
                    throw new IllegalArgumentException("Semua kolom harus diisi.");
                }

                if (komentar.length() < 10) {
                    throw new IllegalArgumentException("Komentar harus memiliki minimal 10 karakter.");
                }

                int rating = Integer.parseInt(ratingText);
                if (rating < 1 || rating > 5) {
                    throw new RatingTidakValidException("Rating harus antara 1 sampai 5.");
                }

                //polimorfisme untuk menentukan jenis ulasan berdasarkan checkbox VIP
                Ulasan ulasan;
                if (chkVIP.isSelected()) {
                    UlasanPrioritas ulasanVip = new UlasanPrioritas();
                    ulasanVip.setMemberVip(true);
                    ulasan = ulasanVip;
                } else {
                    ulasan = new Ulasan();
                }

                ulasan.setIdUlasan(nextId++);
                ulasan.setProduk(produk);
                ulasan.setKomentar(komentar);
                ulasan.setRating(rating);
                service.tambahUlasan(ulasan);
                nextIdField.setText(String.valueOf(nextId));
                JOptionPane.showMessageDialog(this, "Ulasan berhasil ditambahkan.", "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            } catch (RatingTidakValidException ex) {
                JOptionPane.showMessageDialog(this, "Rating harus berupa angka 1 sampai 5.", "Input Salah",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        updateButton.addActionListener(e -> {
            try {
                String idText = idField.getText().trim();
                String komentar = komentarField.getText().trim();
                String ratingText = ratingField.getText().trim();

                if (idText.isEmpty() || komentar.isEmpty() || ratingText.isEmpty()) {
                    throw new IllegalArgumentException("ID, komentar, dan rating harus diisi untuk update.");
                }

                int id = Integer.parseInt(idText);
                int rating = Integer.parseInt(ratingText);
                if (rating < 1 || rating > 5) {
                    throw new RatingTidakValidException("Rating harus antara 1 sampai 5.");
                }

                service.updateUlasan(id, komentar, rating);
                JOptionPane.showMessageDialog(this, "Update ulasan selesai.", "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID dan rating harus angka.", "Input Salah",
                        JOptionPane.ERROR_MESSAGE);
            } catch (RatingTidakValidException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Rating Tidak Valid", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        //filter ulasan VIP
        btnFilterVIP.addActionListener(e -> {
            tableModel.setRowCount(0);
            java.util.List<Ulasan> daftarVip = service.filterUlasanVip();
            
            for (Ulasan ulasan : daftarVip) {
                Vector<Object> row = new Vector<>();
                row.add(ulasan.getIdUlasan());

                if (ulasan instanceof UlasanPrioritas) {
                    UlasanPrioritas ulasanVip = (UlasanPrioritas) ulasan;
                    row.add(ulasanVip.getProduk() + " (VIP)");
                } else {
                    row.add(ulasan.getProduk());
                }

                row.add(ulasan.getKomentar());
                row.add(ulasan.getRating());
                tableModel.addRow(row);
            }

        });

        deleteButton.addActionListener(e -> {
            try {
                String idText = idField.getText().trim();
                if (idText.isEmpty()) {
                    throw new IllegalArgumentException("ID harus diisi untuk menghapus ulasan.");
                }
                int id = Integer.parseInt(idText);
                service.hapusUlasan(id);
                JOptionPane.showMessageDialog(this, "Ulasan dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID harus berupa angka.", "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        clearButton.addActionListener(e -> {
            pembeliField.setText("");
            penjualField.setText("");
            idField.setText("");
            produkField.setText("");
            ratingField.setText("");
            komentarField.setText("");
        });

        refreshButton.addActionListener(e -> refreshTable());
        refreshTable();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 520);
        setLocationRelativeTo(null);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        java.util.List<Ulasan> reviews = service.getDaftarUlasan();
        for (Ulasan ulasan : reviews) {
            Vector<Object> row = new Vector<>();
            row.add(ulasan.getIdUlasan());
            row.add(ulasan.getProduk());
            row.add(ulasan.getKomentar());
            row.add(ulasan.getRating());
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
