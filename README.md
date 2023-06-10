2023-1 스마트폰응용프로그래밍 과제 모음

<br><br>

## 사용 도구
- Android
- Java
- AndroidStudio

<br>

## Content

[1. UP Down 게임 만들기](#1-Up-Down-게임-만들기)<br>
[2. 계수기 어플](#2-계수기-어플)<br>
[3. 타이머 어플](#3-타이머-어플)<br>
[4. 텀프로젝트](#Term-Project-일정-관리-어플)<br>

<br>

### 1. Up Down 게임 만들기
<img src="https://user-images.githubusercontent.com/64102831/229367986-4e6f5bf7-ed93-40e2-98b6-366eb520d1e1.png" width="300"/><img src="https://user-images.githubusercontent.com/64102831/229367987-eaf3a040-24bd-4954-bd69-94d091e9d30d.png" width="300"/><img src="https://user-images.githubusercontent.com/64102831/229367988-aa39fe94-dc66-4b36-9128-8b19534f7c6b.png" width="300"/><br>
시스템이 생성한 1~100 사이의 난수를 맞추는 게임 어플입니다. 난수보다 높은 수를 제시하면 "Down", 난수보다 낮은 수를 제시하면 "Up"이라고 알려줍니다.<br>
##### 구현한 기능
- 기본 기능 구현
- GUESS 버튼을 누를 때 마다 이미지가 바뀜
- 사용자가 시도한 횟수를 추적하여 남은 목숨을 구현, 목숨이 0이 되면 실패 처리
- 쓰레드를 활용하여 남은 시간 기능을 구현, 1초가 지날때마다 남은 시간 업데이트
- Reset 버튼을 구현, 버튼을 누르면 모든 값이 초기값으로 변경

<br>

### 2. 계수기 어플
<img src="https://user-images.githubusercontent.com/64102831/229367989-84150937-2ac6-4b0c-a16a-9b2ef024237d.png" width="300"><img src="https://user-images.githubusercontent.com/64102831/229367990-b91f9e15-a10e-48d2-9c96-8617f8b60327.png" width="300"><img src="https://user-images.githubusercontent.com/64102831/229367984-8adaa6cf-d90f-4deb-878c-051e55e91fe4.png" width="300"><br>
계수기 어플입니다. 버튼을 누를때마다 숫자가 증가/감소합니다.
- 기본 기능 구현
  - 증가버튼: 숫자 증가
  - 감소버튼: 숫자 감소
- 리셋 버튼 구현: 모든 상태가 초기화
- step 기능 구현: 내가 입력한 숫자만큼 증가/감소

<br>

### 3. 타이머 어플
<img src="https://user-images.githubusercontent.com/64102831/229367985-88e70423-c864-4de7-8fde-d2d0e6f7904f.png" width="300"><br>
타이머 어플입니다. Timer, TimerTask 클래스를 활용하여 타이머 스레드 동작을 구현했고, 커스텀뷰를 활용하여 남은 시간을 시각적으로 표현합니다.
- 기본 기능 구현: 분과 초를 설정하고 Start 버튼을 누르면 시간 측정 시작
- Stop 버튼: 타이머 일시 정지
- CustomView: 남은 시간 시각화
  - 안쪽 초록색: 남은 초(second)
  - 바깥쪽 파란색: 남은 분(minute)
  
  <br>
  
  ### Term Project: 일정 관리 어플
  일정 관리 어플입니다. 사용한 기술 및 구현 기능은 다음과 같습니다.
-	사용자 선택
    - 사용자 선택: 사용자를 추가하여, 사용자 별로 각자의 일정을 추가할 수 있습니다.
-	날짜 선택
    - 날짜를 선택하면 해당 날짜의 일정을 요약하여 보여줍니다.
    - 해당 날짜의 지출 예정 금액을 보여줍니다.
-	일정 관리
    - 특정 날짜에 일정을 추가하여 관리할 수 있습니다.
    - 일정을 삭제, 수정할 수 있습니다.
    - 일정을 완료했는지 체크할 수 있습니다.
-	DataBase
    - SQLite를 사용하여 데이터를 관리합니다. 앱을 종료 후에도 정보가 남아있습니다.

##### 실행 화면
- 사용자 선택 및 추가
    -사용자를 선택, 추가합니다. 각 사용자마다 별도로 일정을 관리할 수 있습니다.
<img src="https://github.com/haZuny/SCH-android-programming/assets/64102831/47252ca2-103e-4014-a4a1-7bb14f6a65c5" width="300"><br>
<img src="https://github.com/haZuny/SCH-android-programming/assets/64102831/dcfdf75e-5c9f-4474-87f9-59026e7f6bac" width="300"><br>

- 메인 페이지
  - 쉽게 일정을 확인 할 수 있도록 달력을 제공합니다.
  - 달력에서 특정 날짜를 선택하면 해당 날짜의 일정을 요약하여 보여줍니다.
<img src="https://github.com/haZuny/SCH-android-programming/assets/64102831/38c69350-e7a0-4094-b6d2-41a11b8dad5f" width="300"><br>

- 일정 추가
  - 일정을 추가하는 인터페이스를 제공합니다.
  - 시간 같은 경우, 버튼을 클릭하면 TimePicker를 제공하여 쉽게 시간을 선택할 수 있습니다.
<img src="https://github.com/haZuny/SCH-android-programming/assets/64102831/12529aec-fffe-4537-969c-06cc2507fbd3" width="300"><br>
<img src="https://github.com/haZuny/SCH-android-programming/assets/64102831/e4e79f85-1778-43d6-a550-5021e13e6860" width="300"><br>
  
- 오늘 일정 보기
  - 오늘 일정을 좀 더 자세히 확인 할 수 있습니다.
- Switch 토글을 통해 일정 완료 여부를 체크할 수 있습니다.
<img src="https://github.com/haZuny/SCH-android-programming/assets/64102831/611dadde-f95c-431f-8b9d-d2f62d2c8b1e" width="300"><br>

- 오늘 일정 자세히 보기
  - 일정을 자세히 확인할 수 있습니다.
  - 일정을 수정, 삭제 할 수 있습니다.
<img src="https://github.com/haZuny/SCH-android-programming/assets/64102831/3f3cf745-284a-4d3e-989f-831ea5068efa" width="300"><br>
<img src="https://github.com/haZuny/SCH-android-programming/assets/64102831/48d90b76-ce9e-44eb-b225-4fcf0462800e" width="300"><br>

##### DB 모델링
<img src="https://github.com/haZuny/SCH-android-programming/assets/64102831/2947622c-943a-4898-a5b1-25e4a39a93d2" width="300"><br>
