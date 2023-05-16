package jmu.dao.impl;

import jmu.dao.INoteDAO;
import jmu.db.ConnectionManager;
import jmu.utils.MyBatisUtils;
import jmu.vo.Note;
import jmu.vo.Person;
import jmu.vo.Revert;
import org.apache.ibatis.session.SqlSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NoteDAOImpl implements INoteDAO {
    // 增加操作
    public void insert(Note note) throws Exception
    {
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        sqlSession.insert("jmu.mapper.NoteMapper.insert",note);
//        sqlSession.commit();	//提交事务
//        sqlSession.close();


        String sql = "INSERT INTO note(title,author,content,writeDate,flag,img,authorimg) VALUES(?,?,?,?,?,?,?)" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,note.getTitle()) ;
            pstmt.setString(2,note.getAuthor()) ;
            pstmt.setString(3,note.getContent()) ;
            pstmt.setString(4,note.getTime());
            pstmt.setString(5,note.getFlag());
            pstmt.setString(6,note.getImg());
            pstmt.setString(7, note.getAuthorimg());
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
    // 修改操作
    public void update(Note note) throws Exception
    {
        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
        sqlSession.update("jmu.mapper.NoteMapper.update",note);
        sqlSession.commit();	//提交事务
        sqlSession.close();
//        String sql = "UPDATE note SET title=?,author=?,content=? WHERE id=?" ;
//        Connection conn=null;
//        PreparedStatement pstmt=null;
//        try
//        {
//            conn=ConnectionManager.getConnection();
//            pstmt=conn.prepareStatement(sql);
//            pstmt.setString(1,note.getTitle()) ;
//            pstmt.setString(2,note.getAuthor()) ;
//            pstmt.setString(3,note.getContent()) ;
//            pstmt.setInt(4,note.getId()) ;
//            pstmt.executeUpdate() ;
//        }
//        catch (Exception e)
//        {
//            throw new Exception("操作中出现错误！！！") ;
//        }
//        finally
//        {
//            ConnectionManager.closeStatement(pstmt);
//            ConnectionManager.closeConnection(conn);
//        }
    }
    // 删除操作
    public void delete(int id) throws Exception
    {
        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
        Note note=new Note();
        note.setId(id);
        sqlSession.delete("jmu.mapper.NoteMapper.delete",note);
        sqlSession.commit();	//提交事务
        sqlSession.close();


//        String sql = "DELETE FROM note WHERE id=?" ;
////        String sql1 = "DELETE FROM revert WHERE noteID=?" ;
//        Connection conn=null;
//        PreparedStatement pstmt=null;
//
//            try {
//                conn = ConnectionManager.getConnection();
//                pstmt = conn.prepareStatement(sql);
//                pstmt.setInt(1, id);
//                pstmt.executeUpdate();
//            } catch (Exception e) {
//                throw new Exception("操作中出现错误！！！");
//            } finally {
//                ConnectionManager.closeStatement(pstmt);
//                ConnectionManager.closeConnection(conn);
//            }
    }
    // 按ID查询，主要为更新使用
    public Note queryById(int id) throws Exception
    {
        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
        Note note=new Note();
        note.setId(id);
        Note note1=sqlSession.selectOne("jmu.mapper.NoteMapper.queryById",note);
        sqlSession.close();
//        Note note = null ;
//        String sql = "SELECT id,title,author,content FROM note WHERE id=?" ;
//        Connection conn=null;
//        PreparedStatement pstmt=null;
//        ResultSet rs=null;
//
//        try
//        {
//            conn=ConnectionManager.getConnection();
//            pstmt=conn.prepareStatement(sql);
//            pstmt.setInt(1,id) ;
//            rs = pstmt.executeQuery() ;
//            if(rs.next())
//            {
//
//                note = new Note() ;
//                note.setId(rs.getInt(1)) ;
//                note.setTitle(rs.getString(2)) ;
//                note.setAuthor(rs.getString(3)) ;
//                note.setContent(rs.getString(4)) ;
//            }
//        }
//        catch (Exception e)
//        {
//            throw new Exception("操作中出现错误！！！") ;
//        }
//        finally
//        {
//            ConnectionManager.closeResultSet(rs);
//            ConnectionManager.closeStatement(pstmt);
//            ConnectionManager.closeConnection(conn);
//        }
        return  note1;
    }
    // 查询全部
    public List<Note> queryAll(String flag) throws Exception
    {
        if(flag==null)flag="0";
        List<Note> all = new ArrayList<Note>() ;
        String sql = "SELECT id,title,author,content,writeDate,flag,img,authorimg FROM note" ;
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
                if(flag.equals("0")){
                    Note note = new Note();
                    note.setId(rs.getInt(1));
                    note.setTitle(rs.getString(2));
                    note.setAuthor(rs.getString(3));
                    note.setContent(rs.getString(4));
                    note.setTime(rs.getString(5));
                    note.setFlag(rs.getString(6));
                    note.setImg(rs.getString(7));
                    note.setAuthorimg(rs.getString(8));
                all.add(note);
                }else{
                    Note note = new Note();
                    note.setId(rs.getInt(1));
                    note.setTitle(rs.getString(2));
                    note.setAuthor(rs.getString(3));
                    note.setContent(rs.getString(4));
                    note.setTime(rs.getString(5));
                    note.setFlag(rs.getString(6));
                    note.setImg(rs.getString(7));
                    note.setAuthorimg(rs.getString(8));
                    if(flag.equals(note.getFlag())) {
                        all.add(note);
                    }
                }
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
    // 模糊查询
    public List<Note> queryByLike(String cond) throws Exception
    {
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        List<Note> note=sqlSession.selectList("jmu.mapper.NoteMapper.queryByLike",cond);
        List<Note> all = new ArrayList<Note>() ;
        String sql = "SELECT id,title,author,content,writeDate,img,authorimg FROM note WHERE title LIKE ? or AUTHOR LIKE ? or CONTENT LIKE ?" ;
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
                Note note = new Note() ;
                note.setId(rs.getInt(1)) ;
                note.setTitle(rs.getString(2)) ;
                note.setAuthor(rs.getString(3)) ;
                note.setContent(rs.getString(4)) ;
                note.setTime(rs.getString(5));
                note.setImg(rs.getString(6));
                note.setAuthorimg(rs.getString(7));
                all.add(note) ;
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
    public List<Note> queryAllmy(String my) throws Exception {
        List<Note> all = new ArrayList<Note>() ;
        String sql = "SELECT id,title,author,content,writeDate,flag,img,authorimg FROM note where author=?" ;
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
                    Note note = new Note();
                    note.setId(rs.getInt(1));
                    note.setTitle(rs.getString(2));
                    note.setAuthor(rs.getString(3));
                    note.setContent(rs.getString(4));
                    note.setTime(rs.getString(5));
                    note.setFlag(rs.getString(6));
                    note.setImg(rs.getString(7));
                    note.setAuthorimg(rs.getString(8));
                    all.add(note);
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
}
