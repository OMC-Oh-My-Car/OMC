import { createGlobalStyle } from 'styled-components';
import resetStyle from 'styled-reset';

const GlobalStyles = createGlobalStyle`

	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap');

  ${resetStyle}
	// 아래에 전역 스타일을 추가
	* {
		box-sizing: border-box;
	}
	a {
    text-decoration : none;
    color : inherit;
  }
	body {
		font-family: 'Noto Sans KR', sans-serif;
	}
	`;

export default GlobalStyles;
