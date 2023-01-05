# FE 코딩 컨벤션

1. 폴더는 소문자로 작성
2. 컴포넌트 파일과 css는 camelCase로 작성하되 컴포넌트 파일 첫 글자는 대문자로 작성.
3. 컴포넌트는 화살표 함수를 사용하여 작성.
4. 컴포넌트와 페이지는 dummy.jsx, dummy.styled.js로 나누어 스타일 코드 분리.
5. API 관련 함수는 modules 폴더에 작성.
    1. index.js 파일에 있는 인스턴스를 import 하여 사용.  
6. API 함수와 관련되지 않은 함수는 utils 폴더에 작성.
7. page파일은 styled-components를 사용하여 container를 작성 후 적용, 그 안에 컴포넌트 파일 추가하기.
8. 컴포넌트 파일은 컴포넌트 별로 폴더를 작성 후 그 안에 파일 작성.
    1. 컴포넌트는 재 사용할 수 있는 최소 UI 단위로 분리.
    2. 하나의 컴포넌트를 만들 때 단계별로 분리하기
<img src='https://user-images.githubusercontent.com/91947958/210709140-43831f19-b947-45eb-9268-ef07bbd95255.png' width='400'>
<8.2 예시파일> 
