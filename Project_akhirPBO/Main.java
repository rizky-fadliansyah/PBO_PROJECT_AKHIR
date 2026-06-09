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

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
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

        JMenuBar menuBar = new JMenuBar();
        JMenu menuAksi = new JMenu("Aksi");
        JMenuItem menuItemCekId = new JMenuItem("Periksa ID Ulasan");
        JMenuItem menuItemTambahInteraktif = new JMenuItem("Tambah Ulasan Interaktif");
        JMenuItem menuItemFilterSort = new JMenuItem("Filter/Sorting Ulasan");
        menuAksi.add(menuItemCekId);
        menuAksi.add(menuItemTambahInteraktif);
        menuAksi.add(menuItemFilterSort);
        menuBar.add(menuAksi);
        setJMenuBar(menuBar);

        menuItemCekId.addActionListener(e -> {
            try {
                String inputId = JOptionPane.showInputDialog(this, "Masukkan ID Ulasan untuk diperiksa:", "Periksa ID", JOptionPane.QUESTION_MESSAGE);
                validateIdInput(inputId);
                JOptionPane.showMessageDialog(this, "ID ulasan valid: " + inputId, "Validasi Berhasil", JOptionPane.INFORMATION_MESSAGE);
            } catch (InputTidakValidException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        menuItemTambahInteraktif.addActionListener(e -> {
            try {
                tambahUlasanInteraktif(nextIdField);
                refreshTable();
            } catch (InputTidakValidException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        menuItemFilterSort.addActionListener(e -> {
            String[] options = {"Filter Rating >= 4", "Urutkan Rating Menurun", "Urutkan Rating Menaik", "Tampilkan Semua"};
            int choice = JOptionPane.showOptionDialog(this,
                    "Pilih aksi filter/sorting:",
                    "Filter/Sorting",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (choice == 0) {
                refreshTable(filterReviews(4));
            } else if (choice == 1) {
                refreshTable(sortReviews(true));
            } else if (choice == 2) {
                refreshTable(sortReviews(false));
            } else if (choice == 3) {
                refreshTable();
            }
        });

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

                int rating = Integer.parseInt(ratingText);
                if (rating < 1 || rating > 5) {
                    throw new IllegalArgumentException("Rating harus antara 1 sampai 5.");
                }

                Ulasan ulasan = new Ulasan();
                ulasan.setIdUlasan(nextId++);
                ulasan.setProduk(produk);
                ulasan.setKomentar(komentar);
                ulasan.setRating(rating);
                service.tambahUlasan(ulasan);
                nextIdField.setText(String.valueOf(nextId));
                JOptionPane.showMessageDialog(this, "Ulasan berhasil ditambahkan.", "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Rating harus berupa angka 1 sampai 5.", "Input Salah",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (InputTidakValidException ex) {
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
                    throw new IllegalArgumentException("Rating harus antara 1 sampai 5.");
                }

                service.updateUlasan(id, komentar, rating);
                JOptionPane.showMessageDialog(this, "Update ulasan selesai.", "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID dan rating harus angka.", "Input Salah",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (InputTidakValidException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
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
            } catch (InputTidakValidException ex) {
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
        refreshTable(service.getDaftarUlasan());
    }

    private void refreshTable(java.util.List<Ulasan> reviews) {
        tableModel.setRowCount(0);
        for (Ulasan ulasan : reviews) {
            Vector<Object> row = new Vector<>();
            row.add(ulasan.getIdUlasan());
            row.add(ulasan.getProduk());
            row.add(ulasan.getKomentar());
            row.add(ulasan.getRating());
            tableModel.addRow(row);
        }
    }

    private void tambahUlasanInteraktif(JTextField nextIdField) throws InputTidakValidException {
        String produk = JOptionPane.showInputDialog(this, "Masukkan nama produk:", "Tambah Ulasan", JOptionPane.QUESTION_MESSAGE);
        if (produk == null) {
            return;
        }
        if (produk.trim().isEmpty()) {
            throw new InputTidakValidException("Produk harus diisi.");
        }

        String ratingText = JOptionPane.showInputDialog(this, "Masukkan rating [1-5]:", "Tambah Ulasan", JOptionPane.QUESTION_MESSAGE);
        if (ratingText == null) {
            return;
        }
        int rating;
        try {
            rating = Integer.parseInt(ratingText.trim());
        } catch (NumberFormatException ex) {
            throw new InputTidakValidException("Rating harus berupa angka bulat.", ex);
        }
        if (rating < 1 || rating > 5) {
            throw new InputTidakValidException("Rating harus antara 1 sampai 5.");
        }

        String komentar = JOptionPane.showInputDialog(this, "Masukkan komentar:", "Tambah Ulasan", JOptionPane.QUESTION_MESSAGE);
        if (komentar == null) {
            return;
        }
        if (komentar.trim().isEmpty()) {
            throw new InputTidakValidException("Komentar harus diisi.");
        }

        Ulasan ulasan = new Ulasan();
        ulasan.setIdUlasan(nextId++);
        ulasan.setProduk(produk.trim());
        ulasan.setKomentar(komentar.trim());
        ulasan.setRating(rating);
        service.tambahUlasan(ulasan);
        nextIdField.setText(String.valueOf(nextId));
        JOptionPane.showMessageDialog(this, "Ulasan berhasil ditambahkan melalui menu interaktif.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }

    private java.util.List<Ulasan> filterReviews(int minRating) {
        java.util.List<Ulasan> reviews = service.getDaftarUlasan();
        reviews.removeIf(ulasan -> ulasan.getRating() < minRating);
        return reviews;
    }

    private java.util.List<Ulasan> sortReviews(boolean descending) {
        java.util.List<Ulasan> reviews = service.getDaftarUlasan();
        reviews.sort((a, b) -> descending ? Integer.compare(b.getRating(), a.getRating()) : Integer.compare(a.getRating(), b.getRating()));
        return reviews;
    }

    private void validateIdInput(String idText) throws InputTidakValidException {
        if (idText == null || idText.trim().isEmpty()) {
            throw new InputTidakValidException("ID tidak boleh kosong.");
        }
        try {
            int id = Integer.parseInt(idText.trim());
            if (id <= 0) {
                throw new InputTidakValidException("ID harus bilangan positif.");
            }
        } catch (NumberFormatException ex) {
            throw new InputTidakValidException("ID harus berupa angka bulat.", ex);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
