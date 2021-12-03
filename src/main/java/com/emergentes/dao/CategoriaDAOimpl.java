package com.emergentes.dao;

import com.emergentes.modelo.Categoria;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOimpl extends ConexionDB implements CategoriaDAO {

    @Override
    public void insert(Categoria categoria) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO categoria (nombre) values (?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Categoria categoria) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE categoria SET nombre = ? WHERE id = ?");
            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM categoria WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        
    }

    @Override
    public Categoria getById(int id) throws Exception {
        Categoria cat = new Categoria();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM categoria WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                cat.setId(rs.getInt("id"));
                cat.setNombre(rs.getString("nombre"));

            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return cat;
    }

    @Override
    public List<Categoria> getAll() throws Exception {
        List<Categoria> lista = null;
        try { 
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM categoria");
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Categoria>();
            while(rs.next()){
                Categoria cat = new Categoria();
                
                cat.setId(rs.getInt("id"));
                cat.setNombre(rs.getString("nombre"));
                lista.add(cat);
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
