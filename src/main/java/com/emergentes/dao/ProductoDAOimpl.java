package com.emergentes.dao;

import com.emergentes.modelo.Producto;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOimpl extends ConexionDB implements ProductoDAO {

    @Override
    public void insert(Producto producto) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO producto (cat_id,nombre,precio_compra,precio_venta,date) values (?,?,?,?,?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, producto.getCat_id());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getPrecio_compra());
            ps.setFloat(4, producto.getPrecio_venta());
            ps.setDate(5, producto.getDate());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Producto producto) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE producto SET cat_id = ?,nombre = ?,precio_compra = ?,precio_venta = ?,date = ? WHERE id = ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, producto.getCat_id());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getPrecio_compra());
            ps.setFloat(4, producto.getPrecio_venta());
            ps.setDate(5, producto.getDate());
            ps.setInt(6, producto.getId());
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
            String sql = "DELETE FROM producto WHERE id = ?";
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
    public Producto getById(int id) throws Exception {
        Producto pro = new Producto();
        try {
            this.conectar();
            String sql = "SELECT * FROM producto WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pro.setId(rs.getInt("id"));
                pro.setCat_id(rs.getInt("cat_id"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio_compra(rs.getFloat("precio_compra"));
                pro.setPrecio_venta(rs.getFloat("precio_venta"));
                pro.setDate(rs.getDate("date"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return pro;
    }

    @Override
    public List<Producto> getAll() throws Exception {
        List<Producto> lista = null;
        try {
            this.conectar();
             
            
            
            String sql = "SELECT p.*, c.nombre as categoria FROM producto p ";
            sql += "LEFT JOIN categoria c ON p.cat_id = c.id;";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Producto>();
            while(rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setCat_id(rs.getInt("cat_id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio_compra(rs.getFloat("precio_compra"));
                p.setPrecio_venta(rs.getFloat("precio_venta"));
                p.setCategoria(rs.getString("categoria"));
                p.setDate(rs.getDate("date"));
                lista.add(p);
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
