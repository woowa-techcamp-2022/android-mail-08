# android-mail-08 [DAEHYEONJO]

### 공통 기능 요구사항
1. 화면 디지안으로 적잘한 Component 예측
2. Vector Asset 활용
3. DarkMode 고려한 Style, Theme 적용

### Login 화면 기능 요구사항
1. 닉네임 규칙 (숫자 + 영문 조합 4자리 .. 12자리) Validation
2. 이메일 규칙 Validation
3. 닉네임, 이메일 입력 가능한 TextInputLayout 생성
4. 닉네임, 이메일 모두 Valid한 경우 Next 버튼 활성화
5. 기기 화면 회전시 데이터 유지
6. 다음버튼 클릭시 메인 화면으로 사용자 입력 데이터 전달

### Main 화면 기능 요구사항
1. BottomNavagation View 구성
2. menu item은 Mail, Setting 두가지가 존재한다.
3. Navigation View 구성
4. menu item은 Primary, Social, Promotion 세가지가 존재한다.
5. 초기 화면은 MailFragment의 Primary Email List가 출력되어야 한다.
6. Setting Tab을 클릭하게 된 경우 Backstack에 추가한다.
7. 6번에서 뒤로가기를 누르면 MailFragment가 출력된다.
8. MailFragment의 Navigation Menu Item은 어떤 경우에도 보존되어야 한다.
9. 화면 회전시 width >= 600dp인 기기인 경우, BottomNavigation -> Navigation Rail로 변경
10. Configuration Change시에도 현재 선택된 탭 및 menu item은 유지되어야 한다.

### Mail Fragment 기능 요구사항
1. 상이한 Mail Type에 따라 각 각 다른 Mail List 출력
2. Mail 정보는 발신인, 제목, 본문, 발신일 정보가 있다.
3. 더미 리스트를 생성
4. 발신인의 시작 문자가 영문인 경우 발신인 아이콘에 첫문자를 표기하고 가독성 높은 배경색을 지정한다.
5. 영문자가 시작 문자가 아닌 경우, Vector Asset을 아이콘으로 지정한다.

### Setting Fragment 기능 요구사항
1. 로그인할때 밭은 닉네임, 이메일을 출력한다.
2. 뒤로가기 버튼 누를 경우 Mail Fragment가 출력된다.
