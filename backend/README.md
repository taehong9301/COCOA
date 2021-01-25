# REST API

## Member

### 회원 가입

```
POST /rest/api/v1/member
```

```
{
  "username" : "사용자 username",
  "email" : "사용자 email",
  "password" : "사용자 비밀번호",
  "name": "사용자 이름",
  "phone": "사용자 휴대폰 번호",
  "address": "사용자 주소"
}
```

### 로그인

```
POST /rest/api/v1/authentication 
```

Base64로 인코딩한뒤 헤더의 Authorization 에 담아서 전송

```
# header
usrename:password
```

### 회원 정보 수정

```
PUT /rest/api/v1/member/{id}
```

```
# header
usrename:password
```

```
{
  "username" : "사용자 username",
  "email" : "사용자 email",
  "password" : "사용자 비밀번호",
  "name": "사용자 이름",
  "phone": "사용자 휴대폰 번호",
  "address": "사용자 주소"
}
```

### 회원 탈

```
DELETE /rest/api/v1/member/{id}
```

```
# header
usrename:password
```

```
{퇴
  "username" : "사용자 username",
  "email" : "사용자 email",
  "password" : "사용자 비밀번호",
  "name": "사용자 이름",
  "phone": "사용자 휴대폰 번호",
  "address": "사용자 주소"
}
```
