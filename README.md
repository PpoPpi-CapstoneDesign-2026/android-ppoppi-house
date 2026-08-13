# 뽀삐하우스 (PpoPpiHouse)

반려동물의 건강을 관리하고, AI 기반 피부 진단과 주변 동물병원 검색을 제공하는 Android 애플리케이션입니다.

---

## 프로젝트 소개

뽀삐하우스는 반려동물 보호자가 손쉽게 반려동물의 건강 상태를 파악하고 기록할 수 있도록 돕는 앱입니다.  
카메라로 촬영한 사진과 증상 체크리스트를 바탕으로 AI가 질병 가능성을 분석하고, 긴급도에 따른 조치 방법을 안내합니다.  
또한 달력 형태의 건강 다이어리와 실시간 위치 기반 동물병원 지도를 제공합니다.

---

## 주요 기능

### 1. 카카오 소셜 로그인
카카오 계정으로 간편하게 로그인할 수 있습니다.

<img width="200" height="450" alt="p_로그인 화면" src="https://github.com/user-attachments/assets/68c6addf-7d8b-4097-9739-68af44ccf56d" />

### 2. 반려동물 프로필 관리
- 강아지·고양이를 최대 3마리까지 등록
- 이름, 종, 품종, 나이, 성별, 색상 정보 입력 및 수정
- 홈 화면 상단에서 반려동물을 탭으로 전환

<img width="200" height="450" alt="p_반려동물 등록 화면" src="https://github.com/user-attachments/assets/91f1f4d3-7aea-4ef3-bc48-d3a53567104e" />
<img width="200" height="450" alt="p_등록된 반려동물 화면" src="https://github.com/user-attachments/assets/cef4ae26-7748-469f-b4d1-8f9163efb251" />


### 3. AI 건강 진단
1. 이상 부위를 카메라로 촬영하거나 갤러리에서 선택 (1:1 자동 크롭)
2. 현재 나타나는 증상을 체크리스트에서 선택
3. AI가 질병명, 영향 부위, 심각도(Triage), 권장 조치를 결과 카드로 제공

| 심각도 | 의미 |
|--------|------|
| URGENT | 즉시 응급실 방문 필요 |
| SOON | 빠른 시일 내 진료 권장 |
| MONITOR | 상태 모니터링 |
| NORMAL | 정상 범주 |

<img width="200" height="450" alt="진단 화면 - 안구 촬영" src="https://github.com/user-attachments/assets/49c3737d-51ea-4e93-a9f1-5eea4e3cd16e" />
<img width="200" height="450" alt="진단 화면 - 증상 체크" src="https://github.com/user-attachments/assets/8a2d32f9-b6b9-4988-9a07-dec562e4af31" />
<img width="200" height="500" alt="진단 화면 - 진단 결과" src="https://github.com/user-attachments/assets/eb0ad62c-0fdf-4475-9461-a4f209d27fe5" />


### 4. 홈 화면 질병 검색
키워드로 궁금한 질병 정보를 검색하고, 페이저로 여러 결과를 탐색합니다.

### 5. 건강 다이어리
- 월별 달력에서 날짜를 선택해 일별 기록 조회
- 반려동물별로 메모와 건강 체크리스트 항목 기록
- 기존 다이어리 수정 및 삭제
- 당일 진단 결과가 있으면 다이어리에 자동으로 연동

<img width="200" height="450" alt="image 4" src="https://github.com/user-attachments/assets/1d2554d1-8540-4cbf-a48f-ef69e060be96" />


### 6. 동물병원 지도
- 현재 위치 기반으로 주변 동물병원을 Google Maps에 표시
- 지도를 이동하면 해당 영역 병원 자동 검색
- 24시 응급 필터로 야간 병원 빠르게 찾기
- 마커 탭 시 병원명, 주소, 전화번호, 영업시간 확인

<img width="200" height="450" alt="image 3" src="https://github.com/user-attachments/assets/2ac378a3-4204-4cfe-a9fe-b7dae0e31f41" />

### 7. 설정
- 반려동물 정보 수정 및 추가
- 카메라·위치 권한 설정 페이지로 이동
- 로그아웃 / 회원 탈퇴

---

## 개발 환경

| 항목 | 내용 |
|------|------|
| Language | Kotlin 2.2.0 |
| Min SDK | API 28 (Android 9.0 Pie) |
| Target SDK | API 36 (Android 16) |
| Compile SDK | API 36 |
| JVM Target | 21 |
| Android Studio | Ladybug 이상 권장 |
| AGP | 8.13.2 |
| Gradle | 8.13 |

---

## 아키텍처

MVVM + Clean Architecture 패턴을 따르며, 레이어는 아래와 같이 구성됩니다.

```
app/src/main/java/com/ppoppi/house/
├── data/
│   ├── datasource/      # TokenDataSource (JWT 토큰 저장/관리)
│   ├── model/           # API Request / Response DTO
│   ├── network/         # OkHttp Interceptor (인증, 로깅)
│   ├── repository/      # Repository 구현체
│   └── service/         # Retrofit API 인터페이스
├── di/                  # Hilt 의존성 모듈
├── domain/
│   ├── model/           # 비즈니스 도메인 모델
│   └── repository/      # Repository 인터페이스
└── ui/
    ├── component/       # 공통 Composable 컴포넌트
    ├── diagnosis/       # 진단 플로우 (select → camera → check → result)
    ├── login/           # 로그인 화면
    ├── main/            # 메인 화면 (home / diary / map / setting)
    ├── onboarding/      # 최초 반려동물 등록
    ├── splash/          # 스플래시 화면
    └── theme/           # 색상, 타이포그래피
```

화면 전환 흐름:
```
Splash → Login → Onboarding(최초 1회) → Main(Home / Diary / Map / Setting)
                                              └→ Diagnosis(select → camera → checklist → result)
```

---

## 주요 의존성

| 라이브러리 | 용도 |
|-----------|------|
| Jetpack Compose + Material3 | 선언형 UI |
| Hilt | 의존성 주입 |
| Navigation Compose | 화면 내비게이션 |
| Retrofit + OkHttp + Gson | REST API 통신 |
| CameraX | 카메라 촬영 |
| UCrop | 이미지 1:1 크롭 |
| Coil | 이미지 로딩 |
| Google Maps (maps-compose) | 동물병원 지도 |
| Play Services Location | 현재 위치 |
| Kakao SDK | 소셜 로그인 |
| Accompanist Permissions | 런타임 권한 처리 |
| ktlint | 코드 스타일 |

---

## 실행 방법

### 사전 준비

1. **Android Studio Ladybug 이상** 설치
2. **Android SDK** API 36 설치 (SDK Manager에서 설치 가능)

### 1. 프로젝트 열기

1. Android Studio 실행 → **File → Open**
2. 프로젝트 루트 폴더(`PpoPpiHouse/`) 선택 후 **OK**

### 2. local.properties 설정

프로젝트 루트에 `local.properties` 파일을 생성하고 아래 내용을 작성합니다.

```properties
sdk.dir=<Android SDK 경로>
MAPS_API_KEY=<Google Maps API 키>
KAKAO_API_KEY=<Kakao REST API 키>
BASE_URL=<뽀삐하우스 백엔드 Base URL>
```

| 항목 | 발급 방법 |
|------|----------|
| `MAPS_API_KEY` | Google Cloud Console → Maps SDK for Android 활성화 후 API 키 발급 |
| `KAKAO_API_KEY` | Kakao Developers → 애플리케이션 등록 → REST API 키 사용, 패키지명 `com.ppoppi.house` 등록 필요 |
| `BASE_URL` | 뽀삐하우스 백엔드 서버 주소 |

### 3. Gradle Sync

`local.properties` 작성 후 Android Studio 상단 **Sync Now** 클릭.

### 4. 앱 실행

- **실제 기기**: USB 디버깅 활성화 후 연결 → ▶ 실행
- **에뮬레이터**: Device Manager에서 API 28 이상 AVD 생성 후 ▶ 실행

> 카카오 로그인은 실제 기기에서만 정상 동작합니다. 에뮬레이터에서는 카카오톡 앱이 없으면 로그인이 제한될 수 있습니다.

---

## 문제 해결

| 증상 | 해결 방법 |
|------|----------|
| Gradle sync 실패 | `local.properties`의 `sdk.dir` 경로 확인 |
| 지도가 표시되지 않음 | `MAPS_API_KEY` 값 및 Maps SDK for Android 활성화 여부 확인 |
| 카카오 로그인 실패 | `KAKAO_API_KEY` 값 및 Kakao Developers에서 패키지명 등록 여부 확인 |
| 빌드 오류 (SDK not found) | SDK Manager에서 API 36 설치 |
