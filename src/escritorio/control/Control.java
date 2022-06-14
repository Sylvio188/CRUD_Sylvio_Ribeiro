package escritorio.control;

import escritorio.model.Pessoa;
import escritorio.model.PessoaDAO;
import escritorio.view.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Control implements ActionListener {

    PessoaDAO dao = new PessoaDAO();
    Pessoa p = new Pessoa();
    view view = new view();
    DefaultTableModel modelo = new DefaultTableModel();

    public Control(view v) {
        this.view = v;
        this.view.btnListar.addActionListener(this);
        this.view.btnSalvar.addActionListener(this);
        this.view.btnEditar.addActionListener(this);
        this.view.btnExcluir.addActionListener(this);
        this.view.btnAtualizar.addActionListener(this);
        this.view.btnNovo.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnListar) {
            limparTabla();
            listar(view.tabla);
            novo();
        }
        if (e.getSource() == view.btnSalvar) {
            Adicionar();
            listar(view.tabla);
            novo();
        }
        if (e.getSource() == view.btnEditar) {
            int fila = view.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(view, "Você deve selecionar uma linha..!!");
            } else {
                int id = Integer.parseInt((String) view.tabla.getValueAt(fila, 0).toString());
                String nom = (String) view.tabla.getValueAt(fila, 1);
                String email = (String) view.tabla.getValueAt(fila, 2);
                String tel = (String) view.tabla.getValueAt(fila, 3);
                view.txtId.setText("" + id);
                view.txtNom.setText(nom);
                view.txtEmail.setText(email);
                view.txtTel.setText(tel);
            }
        }
        if (e.getSource() == view.btnAtualizar) {
            Atualizar();
            listar(view.tabla);
            novo();

        }
        if (e.getSource() == view.btnExcluir) {
            Excluir();
            listar(view.tabla);
            novo();
        }
        if (e.getSource() == view.btnNovo) {
            novo();
        }

    }

    void novo() {
        view.txtId.setText("");
        view.txtNom.setText("");
        view.txtTel.setText("");
        view.txtEmail.setText("");
        view.txtNom.requestFocus();
    }

    public void Excluir() {
        int fila = view.tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(view, "Você deve selecionar uma linha..!!");
        } else {
            int id = Integer.parseInt((String) view.tabla.getValueAt(fila, 0).toString());
            dao.Excluir(id);
            System.out.println("O resultado é" + id);
            JOptionPane.showMessageDialog(view, "Usuário Removido...!!!");
        }
        limparTabla();
    }

    public void Adicionar() {
        String nom = view.txtNom.getText();
        String email = view.txtEmail.getText();
        String tel = view.txtTel.getText();
        p.setNom(nom);
        p.setEmail(email);
        p.setTel(tel);
        int r = dao.Adicionar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(view, "Usuario Salvo com Sucesso !");
        } else {
            JOptionPane.showMessageDialog(view, "Erro, não foi possivel salvar !!");
        }
        limparTabla();
    }

    public void Atualizar() {
        if (view.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(view, "O Id não está identificado, você deve selecionar a opção Editar");
        } else {
            int id = Integer.parseInt(view.txtId.getText());
            String nom = view.txtNom.getText();
            String email = view.txtEmail.getText();
            String tel = view.txtTel.getText();
            p.setId(id);
            p.setNom(nom);
            p.setEmail(email);
            p.setTel(tel);
            int r = dao.Atualizar(p);
            if (r == 1) {
                JOptionPane.showMessageDialog(view, "Usuário atualizado com sucesso !");
            } else {
                JOptionPane.showMessageDialog(view, "Erro");
            }
        }
        limparTabla();
    }

    public void listar(JTable tabla) {
        centerCells(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Pessoa> lista = dao.listar();
        Object[] objeto = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNom();
            objeto[2] = lista.get(i).getEmail();
            objeto[3] = lista.get(i).getTel();
            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }

    void centerCells(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < view.tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limparTabla() {
        for (int i = 0; i < view.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
