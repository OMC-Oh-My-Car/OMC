import { createGlobalStyle } from 'styled-components';
import resetStyle from 'styled-reset';

const GlobalStyles = createGlobalStyle`

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
	*::-webkit-scrollbar {
		display: none;
	}
`;

export default GlobalStyles;
