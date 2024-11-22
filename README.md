## 9/11/2024 Completed function for Fee:
Đã hoàn thành đầy đủ chức năng cho mục phí 

## 2/11/2024 Update GUI: 
1. Thêm đủ giao diện chức năng thu phí, các cửa sổ cần thiết khi click vào `button`
2. Thêm các `Class Controller` tương ứng cho các file .fxml giao diện để điều khiển, kết nối các phần tử @FXML đầy đủ, chỉ cho back-end tương tác với phần tử @FXML là được
3. Thêm class `TabController` để chuyển tab, add các phần tử con `AddChildren` là các file .fxml để tương tác cho các TabPane là Trang chủ, Quản lý cư dân, Quản lý thu phí
4. Thêm file `styles.css` với các class phù hợp thoải mái tuỳ biến 

## 13/11/2024 Update GUI
1. Sửa đổi giao diện Main
2. Tạo một file `common > globalVariable` để khai báo các biến toàn cục.
3. Thêm chức năng kiểm tra đăng nhập.
4. Thêm các cảnh cáo khi thoát đăng nhập.
5. Thêm thông báo lỗi khi sai thông tin tài khoản.
6. Tạo một folder `Home > Alert` để thiết kế lại các alert về sau

## 16/11/2024 Update GUI & Completed function for Account
1. Tách thông tin tài khoản từ tab "Trang chủ" thành một tab"Tài khoản"
2. Hoàn thành chức năng sửa thông tin và đổi mật khẩu
3. `GlobalVariable` có thêm các biến _USER_ và _screenBounds_ 

## 22/11/2024 
1. Tách `account_controller.java` thành `account_button_controller.java` và `account_content_controller.java` để dễ dàng kiểm soát
2. Thêm `DataManager.java` là class trung gian để giao tiếp giữa các controller, dùng để update thông tin hiển thị
3. Đã giải quyết xong bài toán update thông tin hiển thị trong Account