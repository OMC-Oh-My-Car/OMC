import { createGlobalStyle } from 'styled-components';
import resetStyle from 'styled-reset';

const GlobalStyles = createGlobalStyle`

  ${resetStyle}
	// 아래에 전역 스타일을 추가
	a {
    text-decoration : none;
    color : inherit;
  }
	body {
  
	}
	`;

export default GlobalStyles;
