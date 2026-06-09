import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class Main extends JFrame {
    private final UlasanService service = new UlasanService();
    private final Printer printer = new Printer();
    private int nextId = 1;
    private final DefaultTableModel tableModel;

    public Main() {
        super("Aplikasi Ulasan Pembeli");
        Vector<String> columns = new Vector<>();
        columns.add("ID");
        columns.add("Produk");
        columns.add("Pembeli");
        columns.add("Toko");
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

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 8));

        formPanel.add(new JLabel("Nama Pembeli:"));
        JTextField pembeliField = new JTextField();
        formPanel.add(pembeliField);

        formPanel.add(new JLabel("Status Pembeli:"));
        JComboBox<String> pembeliStatusBox =
                new JComboBox<>(new String[]{
                        "Pembeli Biasa",
                        "Member VIP"
                });

        formPanel.add(pembeliStatusBox);

        formPanel.add(new JLabel("Nama Toko/Penjual:"));
        JTextField penjualField = new JTextField();
        formPanel.add(penjualField);

        formPanel.add(new JLabel("Status Penjual:"));
        JComboBox<String> penjualStatusBox =
                new JComboBox<>(new String[]{
                        "Penjual Biasa",
                        "Grosir",
                        "Reseller"
                });

        formPanel.add(penjualStatusBox);

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

        JButton addButton    = new JButton("Tambah Ulasan");
        JButton updateButton = new JButton("Update Ulasan");
        JButton deleteButton = new JButton("Hapus Ulasan");
        JButton clearButton  = new JButton("Bersihkan Form");
        JButton printButton  = new JButton("Cetak ke Konsol");
        JButton refreshButton = new JButton("Refresh Daftar");
        JButton filterVipButton = new JButton("Filter Member VIP");

        formPanel.add(addButton);
        formPanel.add(updateButton);
        formPanel.add(deleteButton);
        formPanel.add(clearButton);
        formPanel.add(printButton);
        formPanel.add(refreshButton);
        formPanel.add(filterVipButton);

        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.WEST);
        add(tableScroll, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            try {
                String namaPembeli = pembeliField.getText().trim();
                String namaToko    = penjualField.getText().trim();
                String produk      = produkField.getText().trim();
                String komentar    = komentarField.getText().trim();
                String ratingText  = ratingField.getText().trim();

                if (namaPembeli.isEmpty() || namaToko.isEmpty() || produk.isEmpty()
                        || komentar.isEmpty() || ratingText.isEmpty()) {
                    throw new IllegalArgumentException("Semua kolom harus diisi.");
                }

                int rating = Integer.parseInt(ratingText);
                if (rating < 1 || rating > 5) {
                    throw new IllegalArgumentException("Rating harus antara 1 sampai 5.");
                }

                Pembeli pembeli;

                if (pembeliStatusBox
                        .getSelectedItem()
                        .toString()
                        .equals("Member VIP")) {

                    MemberVIP vip =
                            new MemberVIP();

                    vip.setNama(namaPembeli);
                    vip.setPoinReward(100);

                    pembeli = vip;

                } else {

                    pembeli = new Pembeli();
                    pembeli.setNama(namaPembeli);

                }

                service.tambahPembeli(
                        pembeli
                );

                Penjual penjual;

                String statusPenjual =
                        penjualStatusBox
                                .getSelectedItem()
                                .toString();

                switch (statusPenjual) {

                    case "Grosir":

                        TokoGrosir grosir =
                                new TokoGrosir();

                        grosir.setNamaToko(
                                namaToko
                        );

                        grosir.setStatusToko(
                                "Grosir"
                        );

                        penjual = grosir;

                        break;

                    case "Reseller":

                        Reseller reseller =
                                new Reseller();

                        reseller.setNamaToko(
                                namaToko
                        );

                        reseller.setStatustoko(
                                "Reseller"
                        );

                        penjual = reseller;

                        break;

                    default:

                        PenjualBiasa biasa =
                                new PenjualBiasa();

                        biasa.setNamaToko(
                                namaToko
                        );

                        penjual = biasa;
                }

                service.tambahPenjual(
                        penjual
                );

                Ulasan ulasan = new Ulasan();
                ulasan.setIdUlasan(nextId++);
                ulasan.setProduk(produk);
                ulasan.setKomentar(komentar);
                ulasan.setRating(rating);
                ulasan.setNamaPembeli(pembeli.getNama());
                ulasan.setNamaToko(penjual.getNamaToko());

                ulasan.setStatusPembeli(
                        pembeliStatusBox
                                .getSelectedItem()
                                .toString()
                );

                ulasan.setStatusPenjual(
                        penjualStatusBox
                                .getSelectedItem()
                                .toString()
                );

                service.tambahUlasan(ulasan);
                nextIdField.setText(String.valueOf(nextId));
                JOptionPane.showMessageDialog(this, "Ulasan berhasil ditambahkan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                refreshTable();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Rating harus berupa angka 1 sampai 5.", "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateButton.addActionListener(e -> {
            try {
                String idText     = idField.getText().trim();
                String komentar   = komentarField.getText().trim();
                String ratingText = ratingField.getText().trim();

                if (idText.isEmpty() || komentar.isEmpty() || ratingText.isEmpty()) {
                    throw new IllegalArgumentException("ID, komentar, dan rating harus diisi untuk update.");
                }

                int id     = Integer.parseInt(idText);
                int rating = Integer.parseInt(ratingText);
                if (rating < 1 || rating > 5) {
                    throw new IllegalArgumentException("Rating harus antara 1 sampai 5.");
                }

                if (!service.isUlasanAda(id)) {
                    JOptionPane.showMessageDialog(this, "Ulasan dengan ID " + id + " tidak ditemukan.", "Tidak Ditemukan", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                service.updateUlasan(id, komentar, rating);
                JOptionPane.showMessageDialog(this, "Ulasan berhasil diupdate.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                refreshTable();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID dan rating harus angka.", "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                String idText = idField.getText().trim();
                if (idText.isEmpty()) {
                    throw new IllegalArgumentException("ID harus diisi untuk menghapus ulasan.");
                }

                int id = Integer.parseInt(idText);

                if (!service.isUlasanAda(id)) {
                    JOptionPane.showMessageDialog(this, "Ulasan dengan ID " + id + " tidak ditemukan.", "Tidak Ditemukan", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(this,
                        "Yakin ingin menghapus ulasan ID " + id + "?", "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    service.hapusUlasan(id);
                    JOptionPane.showMessageDialog(this, "Ulasan berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    refreshTable();
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID harus berupa angka.", "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        printButton.addActionListener(e -> printer.cetakData(service));

        filterVipButton.addActionListener(e -> {

            tableModel.setRowCount(0);

            for (Ulasan u :
                    service.getDaftarUlasan()) {

                if (u.getStatusPembeli()
                        .equalsIgnoreCase(
                                "Member VIP")) {

                    Vector<Object> row =
                            new Vector<>();

                    row.add(u.getIdUlasan());
                    row.add(u.getProduk());

                    row.add(u.getNamaPembeli());
                    row.add(u.getStatusPembeli());

                    row.add(u.getNamaToko());
                    row.add(u.getStatusPenjual());

                    row.add(u.getKomentar());
                    row.add(u.getRating());

                    tableModel.addRow(row);
                }
            }
        });
        refreshButton.addActionListener(e -> refreshTable());
        refreshTable();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 550);
        setLocationRelativeTo(null);
    }

    private void refreshTable() {

        tableModel.setRowCount(0);

        for (Ulasan ulasan :
                service.getDaftarUlasan()) {

            Vector<Object> row =
                    new Vector<>();

            row.add(ulasan.getIdUlasan());
            row.add(ulasan.getProduk());

            row.add(ulasan.getNamaPembeli());
            row.add(ulasan.getStatusPembeli());

            row.add(ulasan.getNamaToko());
            row.add(ulasan.getStatusPenjual());

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