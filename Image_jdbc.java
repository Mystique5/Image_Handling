import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

public class Image_jdbc {
    public static void main(String[] args) throws SQLException
    {
        String url="jdbc:postgresql://localhost:5432/ImageDb";
        String user="postgres";
        String password="Mota@11";

    //1. To insert Image in Database
       // String img_path="E:\\OneDrive\\Desktop\\Image\\cute-picture.jpg"; // location of image to be taken from
      //  String query="Insert into image_table(image_data) values(?)"; // To insert in table 

    //2, To retrive image from Database
        String folder_path="E:\\OneDrive\\Desktop\\Image\\"; //location where image has to be inserted
        String query="Select image_data from image_table where image_id=(?)"; // To retrive from table

        try
        {
            Connection con=DriverManager.getConnection(url, user, password);
            System.out.println("Connection established!");
        //1. For insert    
           /*FileInputStream fs=new FileInputStream(img_path);
            byte[] image_data=new byte[fs.available()];
            fs.read(image_data);
            PreparedStatement ps=con.prepareStatement(query); 
            ps.setBytes(1,image_data);
            int affectedrows=ps.executeUpdate();
            if(affectedrows>0)
            {
                System.out.println("Image inserted Successfully!");
            }
            else
            {
                System.out.println("Image not inserted!");
            }*/
        
        //2. For Retrive
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,1);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                byte[] imageData=rs.getBytes("image_data");
                String imagePath=folder_path+"extractedImage.jpg";
                OutputStream outputstream=new FileOutputStream(imagePath);
                outputstream.write(imageData);
            }
            else{
                System.out.println("No image found");
            }

        }
        catch(FileNotFoundException e)
        {
            throw new RuntimeException(e);
        } catch (IOException e) {
      
            e.printStackTrace();
        }


        // con.close();

    }
    
}
