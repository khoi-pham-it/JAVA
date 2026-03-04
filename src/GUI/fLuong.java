package GUI;

import BUS.busLuong;
import BUS.busNhanVien;
import DTO.Luong;
import DTO.NhanVien;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class fLuong extends JFrame {

    private JComboBox<String> cbNhanVien;
    private JTextField txtLuongCoBan, txtPhuCap, txtThang, txtNam;
    private JTable tblLuong;
    private DefaultTableModel model;

    private JButton btnThem, btnSua, btnXoa, btnLamMoi;

    private int selectedID = -1;

    public fLuong() {
        initUI();
        loadComboBox();
        loadTable();
    }

    private void initUI() {

        setTitle("QUẢN LÝ LƯƠNG");
        setSize(950, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new java.awt.Color(0, 128, 128));

        JLabel lblNV = new JLabel("Nhân viên:");
        lblNV.setBounds(30, 20, 100, 25);
        add(lblNV);

        cbNhanVien = new JComboBox<>();
        cbNhanVien.setBounds(140, 20, 250, 25);
        add(cbNhanVien);

        JLabel lblLuong = new JLabel("Lương cơ bản:");
        lblLuong.setBounds(30, 60, 100, 25);
        add(lblLuong);

        txtLuongCoBan = new JTextField();
        txtLuongCoBan.setBounds(140, 60, 250, 25);
        add(txtLuongCoBan);

        JLabel lblPhuCap = new JLabel("Phụ cấp:");
        lblPhuCap.setBounds(30, 100, 100, 25);
        add(lblPhuCap);

        txtPhuCap = new JTextField();
        txtPhuCap.setBounds(140, 100, 250, 25);
        add(txtPhuCap);

        JLabel lblThang = new JLabel("Tháng:");
        lblThang.setBounds(450, 20, 60, 25);
        add(lblThang);

        txtThang = new JTextField();
        txtThang.setBounds(520, 20, 100, 25);
        add(txtThang);

        JLabel lblNam = new JLabel("Năm:");
        lblNam.setBounds(450, 60, 60, 25);
        add(lblNam);

        txtNam = new JTextField();
        txtNam.setBounds(520, 60, 100, 25);
        add(txtNam);

        btnThem = new JButton("Thêm");
        btnThem.setBounds(700, 20, 100, 30);
        add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBounds(700, 60, 100, 30);
        add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(820, 20, 100, 30);
        add(btnXoa);

        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setBounds(820, 60, 100, 30);
        add(btnLamMoi);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
            "ID", "ID NV", "Lương cơ bản", "Phụ cấp", "Tháng", "Năm", "Tổng"
        });

        tblLuong = new JTable(model);
        JScrollPane scroll = new JScrollPane(tblLuong);
        scroll.setBounds(30, 170, 890, 320);
        add(scroll);

        // EVENTS
        btnThem.addActionListener(e -> themLuong());
        btnSua.addActionListener(e -> suaLuong());
        btnXoa.addActionListener(e -> xoaLuong());
        btnLamMoi.addActionListener(e -> lamMoi());

        tblLuong.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                hienThiLenForm();
            }
        });
    }

    // ================= LOAD TABLE =================
    private void loadTable() {

        model.setRowCount(0);
        ArrayList<Luong> list = busLuong.getInstance().getListLuong();

        for (Luong l : list) {
            model.addRow(new Object[]{
                l.getId_luong(),
                l.getId_nv(),
                l.getLuong_co_ban(),
                l.getPhu_cap(),
                l.getThang(),
                l.getNam(),
                l.getTongLuong()
            });
        }
    }

    // ================= LOAD COMBOBOX =================
    private void loadComboBox() {

        cbNhanVien.removeAllItems();
        ArrayList<NhanVien> list = busNhanVien.getInstance().getListNhanVien();

        for (NhanVien nv : list) {
            cbNhanVien.addItem(nv.id_nv + " - " + nv.ten_nv);
        }
    }

    // ================= CLICK TABLE =================
    private void hienThiLenForm() {

        int row = tblLuong.getSelectedRow();
        if (row == -1) return;

        selectedID = Integer.parseInt(model.getValueAt(row, 0).toString());
        String idNV = model.getValueAt(row, 1).toString();

        for (int i = 0; i < cbNhanVien.getItemCount(); i++) {
            if (cbNhanVien.getItemAt(i).startsWith(idNV + " -")) {
                cbNhanVien.setSelectedIndex(i);
                break;
            }
        }

        txtLuongCoBan.setText(model.getValueAt(row, 2).toString());
        txtPhuCap.setText(model.getValueAt(row, 3).toString());
        txtThang.setText(model.getValueAt(row, 4).toString());
        txtNam.setText(model.getValueAt(row, 5).toString());
    }

    // ================= THÊM =================
    private void themLuong() {

        try {
            int id_nv = Integer.parseInt(cbNhanVien.getSelectedItem().toString().split(" - ")[0]);
            int luong = Integer.parseInt(txtLuongCoBan.getText());
            int phuCap = Integer.parseInt(txtPhuCap.getText());
            int thang = Integer.parseInt(txtThang.getText());
            int nam = Integer.parseInt(txtNam.getText());

            if (busLuong.getInstance().checkTrung(id_nv, thang, nam)) {
                JOptionPane.showMessageDialog(this, "Đã tồn tại lương tháng này!");
                return;
            }

            boolean kq = busLuong.getInstance()
                    .insertLuong(id_nv, luong, phuCap, thang, nam);

            if (kq) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                loadTable();
                lamMoi();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nhập đúng định dạng số!");
        }
    }

    // ================= SỬA =================
    private void suaLuong() {

        if (selectedID == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa!");
            return;
        }

        try {
            int id_nv = Integer.parseInt(cbNhanVien.getSelectedItem().toString().split(" - ")[0]);
            int luong = Integer.parseInt(txtLuongCoBan.getText());
            int phuCap = Integer.parseInt(txtPhuCap.getText());
            int thang = Integer.parseInt(txtThang.getText());
            int nam = Integer.parseInt(txtNam.getText());

            boolean kq = busLuong.getInstance()
                    .updateLuong(selectedID, id_nv, luong, phuCap, thang, nam);

            if (kq) {
                JOptionPane.showMessageDialog(this, "Sửa thành công!");
                loadTable();
                lamMoi();
                selectedID = -1;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu!");
        }
    }

    // ================= XÓA =================
    private void xoaLuong() {

        if (selectedID == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn chắc chắn muốn xóa?", "Xác nhận",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            boolean kq = busLuong.getInstance().deleteLuong(selectedID);

            if (kq) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadTable();
                lamMoi();
                selectedID = -1;
            }
        }
    }

    // ================= LÀM MỚI =================
    private void lamMoi() {
        txtLuongCoBan.setText("");
        txtPhuCap.setText("");
        txtThang.setText("");
        txtNam.setText("");
        tblLuong.clearSelection();
        selectedID = -1;
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new fLuong().setVisible(true);
        });
    }
}
