# 알코올 중독 진단 및 치료 보조 프로젝트 API 개발

## 1. Chatbot
POST http://localhost:8080/api/chatbot/voice-upload 

Whisper API

- 음성을 텍스트로 변환(Speech-to-Text)하는 API
- 최대 25MB 크기의 파일을 처리 가능

Text-to-Speech (TTS) API

- 실시간 사용 사례에 최적화된 `tts-1` 모델과 높은 품질에 최적화된 `tts-1-hd` 모델을 제공

### 음성 대화 기능 구현 방안

1. **사용자의 음성 입력을 서버에 m4a와 같은 파일로 POST**
    - 지원되는 파일 형식: m4a, mp3, webm, mp4, mpga, wav, mpeg
2. **음성 파일을** **텍스트로 변환 (STT) -** Whisper API를 사용하여 음성을 텍스트로 변환
3. **텍스트를 GPT-4o Chat API로 전송**
    - 변환된 텍스트를 GPT-4o Chat API로 전송하여 사용자의 요청을 처리
4. **Chat API 응답을 text 형식으로 사용자에게 Post**

## 2. 회원가입
POST http://localhost:8080/api/auth/register

```json
{
    "username": "admin2",
    "password": "admin2"
}

```

## 3. 로그인
POST http://localhost:8080/api/auth/login
```json
{
    "username": "admin2",
    "password": "admin2"
}

```
반환 JSON 값으로 Jwt Token 값, 게정 생성 후 첫 로그인인지 아닌지에 따른 Messeage 도출

## 4. 프로필 생성
POST http://localhost:8080/api/profile/create
```json
{
  "height": 175,
  "gender": "male",
  "weight": 70,
  "birthYear": 1990,
  "monthlyDrinkGoal": 5.5
}

```
정상적으로 저장이 되었다면 입력값과 똑같은 JSON 반환, 프로필 생성 후 첫 로그인으로 판단하지 않음.

## 5. 프로필 업데이트
POST http://localhost:8080/api/profile/update
POST가 정상적으로 성공하면, 기존 정보 삭제 후 삽입

## 6. 음성 파일 to Backend 서버
POST http://localhost:8080/api/chatbot/upload 
Root directory 하위에 있는 uploads 폴더에 저장.
저장 파일명: 사용자ID_yyyymmddhhmmss.확장자 (e.g. admin2_20240701185722.m4a)

## 7. Daily Survey 제출
POST http://localhost:8080/api/survey/submit
```
JSON
{
  "question1": 1,
  "question2": 2,
  "question3": 3,
  "question4": 4,
  "question5": 1,
  "question6": 2,
  "question7": 3,
  "question8": 4,
  "question9": 0,
  "question10": 2
}
```
정상으로 제출 되었다면 "Survey submitted successfully 도출

## 8. Daily Survey 기록 조회
GET http://localhost:8080/api/survey/inquiry
JSON으로 responseId, userId, responseDate, 각각 항목에 대한 점수와 총점 반환
