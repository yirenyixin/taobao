package jmu.dao.impl;

import jmu.dao.IProductDAO;
import jmu.db.ConnectionManager;
import jmu.utils.MyBatisUtils;
import jmu.vo.Product;
import org.apache.ibatis.session.SqlSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements IProductDAO {
    // 查询全部
    public List<Product> queryAll(String flag) throws Exception
    {
        List<Product> all=new ArrayList<Product>();
        if(flag.equals("0")) {
//            SqlSession sqlSession1 = MyBatisUtils.getSession();
//            all = sqlSession1.selectList("jmu.mapper.ProductMapper.getall");
//            sqlSession1.commit();
//            sqlSession1.close();
            String sql = "SELECT product.id,product.name,product.img,product.size,product.type,product.price,product.shopid,product.color,person.name FROM product,person where product.shopid=person.id" ;
            Connection conn=null;
            PreparedStatement pstmt=null;
            ResultSet rs=null;
            try
            {
                conn=ConnectionManager.getConnection();
                pstmt=conn.prepareStatement(sql);
                rs = pstmt.executeQuery() ;
                while(rs.next())
                {
                    Product product=new Product();
                    product.setId(rs.getInt(1));
                    product.setName(rs.getString(2));
                    product.setImg(rs.getString(3));
                    product.setSize(rs.getString(4));
                    product.setType(rs.getString(5));
                    product.setPrice(rs.getString(6));
                    product.setShopid(rs.getString(7));
                    product.setColor(rs.getString(8));
                    product.setShopname(rs.getString(9));
                    all.add(product);
                }
            }
            catch (Exception e)
            {
                System.out.println(e) ;
                throw new Exception("操作中出现错误！！！") ;
            }
            finally
            {
                ConnectionManager.closeResultSet(rs);
                ConnectionManager.closeStatement(pstmt);
                ConnectionManager.closeConnection(conn);
            }
        }else{
            String sql = "SELECT product.id,product.name,product.img,product.size,product.type,product.price,product.shopid,product.color,person.name FROM product,person where type=? and product.shopid=person.id" ;
            Connection conn=null;
            PreparedStatement pstmt=null;
            ResultSet rs=null;
            try
            {
                conn=ConnectionManager.getConnection();
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1,flag) ;
                rs = pstmt.executeQuery() ;
                while(rs.next())
                {
                    Product product=new Product();
                    product.setId(rs.getInt(1));
                    product.setName(rs.getString(2));
                    product.setImg(rs.getString(3));
                    product.setSize(rs.getString(4));
                    product.setType(rs.getString(5));
                    product.setPrice(rs.getString(6));
                    product.setShopid(rs.getString(7));
                    product.setColor(rs.getString(8));
                    product.setShopname(rs.getString(9));
                    all.add(product);
                }
            }
            catch (Exception e)
            {
                System.out.println(e) ;
                throw new Exception("操作中出现错误！！！") ;
            }
            finally
            {
                ConnectionManager.closeResultSet(rs);
                ConnectionManager.closeStatement(pstmt);
                ConnectionManager.closeConnection(conn);
            }
        }
        return all ;
    }
    public List<Product> queryAll1(String id) throws Exception
    {
        SqlSession sqlSession1 =MyBatisUtils.getSession();
        List<Product> all =new ArrayList<Product>();
        List<Product> all1=sqlSession1.selectList("jmu.mapper.ProductMapper.getall");
        for(int i=0;i<all1.size();i++){
            String a= String.valueOf(all1.get(i).getId());
            if(a.equals(id)) {
                all.add(all1.get(i));
            }
        }
        sqlSession1.commit();
        sqlSession1.close();
        return all ;
    }
//    public List<Product> getone(String id) throws Exception {
//        SqlSession sqlSession =MyBatisUtils.getSession();
//        List<Product> =sqlSession.selectOne("jmu.mapper.ProductMapper.getone",id);
//        sqlSession.commit();
//        sqlSession.close();
//        return product;
//    }
    public  List<Product> get_one(String id)throws Exception{
        SqlSession sqlSession=MyBatisUtils.getSession();
        List<Product> pro=sqlSession.selectOne("jmu.mapper.ProductMapper.getone",id);
        sqlSession.commit();
        sqlSession.close();
        return pro;
    }
    // 增加操作
    public void insert(Product product) throws Exception
    {
        String sql1 = "select id from product order by id desc limit 1" ;
//        String sql1 ="SELECT revertID FROM revert where 1 order by revertID asc limit 1,1";



        Connection conn1=null;
        PreparedStatement pstmt1=null;
            conn1= ConnectionManager.getConnection();
            pstmt1=conn1.prepareStatement(sql1);
            ResultSet rs = pstmt1.executeQuery(sql1);
            String id = String.valueOf(0);
            while (rs.next()) {
                id=rs.getString(1);
            }
            int a= Integer.parseInt(id)+1;
            product.setId(a);
//        int a= Integer.parseInt(id)+1;
//        product.setId(String.valueOf(a));
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        sqlSession.insert("jmu.mapper.ProductMapper.insert",product);
//        sqlSession.commit();	//提交事务
//        sqlSession.close();


        String sql = "INSERT INTO product(id,name,img,color,type,price,shopid,size) VALUES(?,?,?,?,?,?,?,?)" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,product.getId()); ;
            pstmt.setString(2,product.getName()) ;
            pstmt.setString(3,product.getImg()) ;
            pstmt.setString(4,product.getColor());
            pstmt.setString(5,product.getType());
            pstmt.setString(6,product.getPrice());
            pstmt.setString(7, product.getShopid());
            pstmt.setString(8, product.getSize());
            pstmt.executeUpdate() ;
        }
        catch (Exception e)
        {
            // System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
    }
    @Override
    public List<Product> queryAllmy(String my) throws Exception {
        List<Product> all = new ArrayList<Product>() ;
        String sql = "SELECT id,name,img,size,type,price,shopid,color FROM product where shopid=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,my) ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                Product product=new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImg(rs.getString(3));
                product.setSize(rs.getString(4));
                product.setType(rs.getString(5));
                product.setPrice(rs.getString(6));
                product.setShopid(rs.getString(7));
                product.setColor(rs.getString(8));
                all.add(product);
            }
        }
        catch (Exception e)
        {
            System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
        return all ;
    }

    @Override
    public List<Product> queryByLike(String cond) throws Exception {
        List<Product> all = new ArrayList<Product>() ;
        String sql = "SELECT product.id,product.name,product.img,product.size,product.type,product.price,product.shopid,product.color,person.name,person.flag FROM product LEFT JOIN person on person.id=product.shopid  WHERE product.id LIKE ? or product.name LIKE ? or person.name LIKE ?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%"+cond+"%") ;
            pstmt.setString(2,"%"+cond+"%") ;
            pstmt.setString(3,"%"+cond+"%") ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                Product product=new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImg(rs.getString(3));
                product.setSize(rs.getString(4));
                product.setType(rs.getString(5));
                product.setPrice(rs.getString(6));
                product.setShopid(rs.getString(7));
                product.setColor(rs.getString(8));
                product.setShopname(rs.getString(9));
                all.add(product);
            }
        }
        catch (Exception e)
        {
            System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
        return all ;
    }

    @Override
    public List<Product> querymyproduct(String id) throws Exception {
        List<Product> all = new ArrayList<Product>() ;
        String sql = "SELECT id,name,img,size,type,price,shopid,color FROM product where shopid=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id) ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                Product product=new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImg(rs.getString(3));
                product.setSize(rs.getString(4));
                product.setType(rs.getString(5));
                product.setPrice(rs.getString(6));
                product.setShopid(rs.getString(7));
                product.setColor(rs.getString(8));
                all.add(product);
            }
        }
        catch (Exception e)
        {
            System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
        return all ;
    }

    @Override
    public void update(Product product) throws Exception {
        String sql = "update product set name=?,img=?,size=?,type=?,price=? where id=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, product.getName()); ;
            pstmt.setString(2, product.getImg());
            pstmt.setString(3, product.getSize());
            pstmt.setString(4, product.getType());
            pstmt.setString(5, product.getPrice());
            pstmt.setInt(6, product.getId());
            pstmt.executeUpdate() ;
        }
        catch (Exception e)
        {
            // System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void delete(Product product) throws Exception {
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        Note note=new Note();
//        note.setId(id);
//        sqlSession.delete("jmu.mapper.NoteMapper.delete",note);
//        sqlSession.commit();	//提交事务
//        sqlSession.close();


        String sql = "DELETE FROM product WHERE id=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;

            try {
                conn = ConnectionManager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, product.getId());
                pstmt.executeUpdate();
            } catch (Exception e) {
                throw new Exception("操作中出现错误！！！");
            } finally {
                ConnectionManager.closeStatement(pstmt);
                ConnectionManager.closeConnection(conn);
            }
    }
}
