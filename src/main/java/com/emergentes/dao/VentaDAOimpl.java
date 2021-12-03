package com.emergentes.dao;

import com.emergentes.modelo.Venta;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOimpl extends ConexionDB implements VentaDAO {

    @Override
    public void insert(Venta venta) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO venta (prod_id,cli_id,cantidad,precio,fecha) values (?,?,?,?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, venta.getProd_id());
            ps.setInt(2, venta.getCli_id());
            ps.setInt(3, venta.getCantidad());
            ps.setFloat(4, venta.getPrecio());
            ps.setDate(5, venta.getFecha());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Venta venta) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE venta SET prod_id = ?,cli_id = ?,cantidad = ?,precio = ?,fecha = ? WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, venta.getProd_id());
            ps.setInt(2, venta.getCli_id());
            ps.setInt(3, venta.getCantidad());
            ps.setFloat(4, venta.getPrecio());
            ps.setDate(5, venta.getFecha());
            ps.setInt(6, venta.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE FROM venta WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Venta getById(int id) throws Exception {
        Venta v = new Venta();
        try {
            this.conectar();
            String sql = "SELECT * FROM venta WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                v.setId(rs.getInt("id"));
                v.setProd_id(rs.getInt("prod_id"));
                v.setCli_id(rs.getInt("cli_id"));
                v.setCantidad(rs.getInt("cantidad"));
                v.setPrecio(rs.getFloat("precio"));
                v.setFecha(rs.getDate("fecha"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return v;
    }

    @Override
    public List<Venta> getAll() throws Exception {
        List<Venta> lista = null;
        try {
            this.conectar();
            //query para consultar uir las tablas producto y cliente
            String sql = "SELECT v.*,p.nombre as producto, c.nombre as cliente FROM venta v ";
            sql += "LEFT JOIN producto p ON v.prod_id = p.id ";
            sql += "LEFT JOIN cliente c ON v.cli_id = c.id";
            //PreparedStatement ps = this.conn.prepareStatement("SELECT v.*, p.nombre as producto, c.nombre as cliente FROM venta v LEFT JOIN producto p ON v.prod_id = p.id LEFT JOIN cliente c ON v.cli_id = c.id");
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Venta>();
            while (rs.next()) {
                Venta v = new Venta();
                v.setId(rs.getInt("id"));
                v.setProd_id(rs.getInt("prod_id"));
                v.setProd_id(rs.getInt("cli_id"));
                v.setCantidad(rs.getInt("cantidad"));
                v.setPrecio(rs.getFloat("precio"));
                v.setFecha(rs.getDate("fecha"));
                v.setCliente(rs.getString("cliente"));
                v.setProducto(rs.getString("producto"));
                lista.add(v);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
}
