import java.io.*;
import java.sql.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import com.addressbook.model.*;

public class AddressBookTester {

    public static void main(String[] args) {
        String excelFilePath = "E:\\NEU\\2024\\Herbst\\T1\\JAVA\\prj\\addressBook\\src\\main\\resources\\addressBook.xls";
        List<FriendPO> friends = readExcelFile(excelFilePath);
        FriendService friendService = new FriendService();

        // 添加朋友
        FriendPO zhaoLiu = new FriendPO("赵六", "+861234567891", "zhaoliu@example.com", "上海市浦东新区");
        friendService.addFriend(zhaoLiu);
        System.out.println("赵六的资料已添加。");

        // 获取所有朋友信息
        System.out.println("所有朋友信息：");
        List<FriendPO> allFriends = friendService.getAllFriends();
        allFriends.forEach(System.out::println);

        // 更新朋友地址
        friendService.updateFriendAddress("赵六", "上海市闵行区");
        System.out.println("赵六的地址已更新。");

        // 删除朋友
        friendService.deleteFriend("赵六");
        System.out.println("赵六的资料已删除。");

        // 再次获取所有朋友信息
        System.out.println("删除赵六后的朋友信息：");
        allFriends = friendService.getAllFriends();
        allFriends.forEach(System.out::println);
    }

    private static List<FriendPO> readExcelFile(String filePath) {
        List<FriendPO> friends = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String name = getCellValue(row.getCell(0));
                String phone = getCellValue(row.getCell(1));
                String email = getCellValue(row.getCell(2));
                String address = getCellValue(row.getCell(3));

                FriendPO friend = new FriendPO(name, phone, email, address);
                friends.add(friend);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return friends;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
