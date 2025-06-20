public class Nguoidung {

    //biến thành viên
String   nguoiDungID;
String   tenDangNhap;
String   matKhau;
String   hoTen;
String   vaitro;

//phương thuc hoat dong



String setName(String hoten){

    hoTen = hoten;
    return hoTen;
}

void getName(){
    System.out.println("Ten:" + hoTen);
}

}