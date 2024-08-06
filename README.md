# Wanted Pre-Onboarding Backend 인턴쉽 과제

> 선택 언어 및 프레임워크 : Java & SpringBoot

</hr>
</br>

### 서비스 개요
- 본 서비스는 기업의 채용을 위한 웹 서비스입니다.
- 회사는 채용공고를 생성하고 이에 사용자는 지원합니다.

</br>

## 1. 요구사항 분석

### 📝 기능
1. 회사는 **채용 공고를 등록**한다.
2. 회사는 **채용 공고를 수정**한다.
3. 회사는 **채용 공고를 삭제**한다.
4. 사용자는 **채용 공고 목록을 확인**한다.
5. 사용자는 **채용 공고 목록에서 검색**을 한다. (선택 및 가산점)
6. 사용자는 **채용 상세 페이지를 확인**할 수 있다.
    1. 채용 내용이 추가적으로 들어있다.
    2. 해당 회사가 올린 다른 채용 공고가 추가적으로 포함된다.(선택 및 가산점)
7. 사용자는 **채용 공고에 지원**한다. (선택 및 가산점)
    1. 사용자는 1회만 지원 가능합니다.

</br>

### 👩‍💻 모델
- 🌆 회사
- 😊 사용자
- 📃 채용공고
- 📑 지원내역(선택사항)

</br>

## 2. 데이터 모델링 설계
![Untitled (2)](https://github.com/user-attachments/assets/0ed6fb4f-2d8e-42b5-b8d0-e5ee9d6167c3)


</br>

## 3. API 설계
| function | url | http api |
|----------|-----|----------|
| 채용 공고 등록 | /recruit/add/{company_id} | POST |
| 채용 공고 수정 | /recruit/edit/{employment_id} | PATCH |
| 채용 공고 삭제 | /recruit/delete/{employment_id} | DELETE |
| 채용 공고 목록 | /recruit/list | GET |
| 채용 공고 상세 페이지 및 다른 공고 리스트 | /recruit/details/{employment_id} | GET |
| 채용 공고 목록 검색 | /recruit/search?search={search_keyword} | GET |
| 채용 공고 지원 | /recruit/apply/{employment_id}/{user_id} | POST |

</br>

## 4. Convention 설계 및 초기 세팅
- [ Convention 관련 wiki 링크 ](https://github.com/K-0joo/wanted-pre-onboarding-backend/wiki/Coding-Conventions){:target="_blank"}
- [ 초기 세팅 관련 wiki 링크 ](https://github.com/K-0joo/wanted-pre-onboarding-backend/wiki/Initialize-Setting-About-SpringBoot){:target="_blank"}

</br>

## 5. 디렉토리 구조

> 🗂 recruit </br>
> |— 🗂 src/main</br>
> |— 🗂 java/wanted/recruit</br>
> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|— 📃 RecruitApplication.java</br>
> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|— 📂 applicant</br>
> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|— 📁 controller</br>
> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|— 📁 dto</br>
> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|— 📁 entity</br>
> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|— 📁 repository</br>
> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|— 📁 service</br>
> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|— 📂 company</br>
> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|— 📁 entity</br>
> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|— 📁 repository</br>
> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|— 📂 employment</br>
> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|— 📁 controller</br>
> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|— 📁 dto</br>
> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|— 📁 entity</br>
> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|— 📁 repository</br>
> |— 📃 .gitignore</br>
> |— 📃 build.gradle

</br>

## 5. 구현 과정
### 5-1. 시스템 환경
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![SpringBoot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
**🗂 jpa**

</br>

### 5-2. 프로젝트 설정
- Git과 인텔리제이 연결
- `build.gradle` 라이브러리 설정
- `application.properties` 설정

</br>

### 5-3. 기능 구현
- [ 상세한 API 확인 POSTMAN API DOCS ](https://documenter.getpostman.com/view/21360094/2sA3rxpYGF){:target="_blank"}
- 아래는 API 테스트할 때 필요한 데이터

</br>

1. 채용 공고 등록
```
http://localhost:8080/recruit/add/{company_id}
```

```
{
    "companyId" : 3,
	"position": "백엔드 주니어 개발자",
    "carrot" : 1000000,
    "content" : "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...",
    "usedTechnique" : "java"
}
```
</br>

2. 채용 공고 수정
```
http://localhost:8080/recruit/edit/{employment_id}
```

```
{
    "companyId" : 1,
    "id" : 3,
    "usedTechnique" : "Django"
}
```

</br>


3. 채용 공고 삭제
```
http://localhost:8080/recruit/delete/{employment_id}
```

```
{
	"employment_id":3
}
```

</br>

4. 채용 공고 리스트 확인
```
http://localhost:8080/recruit/list
```
</br>

5. 채용 공고 검색 리스트를 가져옵니다.
```
http://localhost:8080/recruit/search?search={search_keyword}
```

</br>

6. 채용 공고 상세 페이지 및 같은 회사 다른 공고 확인
```
http://localhost:8080/recruit/details/{employment_id}
```

</br>

7. 사용자 채용 공고 지원
```
http://localhost:8080/recruit/apply/{employment_id}/{user_id}
```
