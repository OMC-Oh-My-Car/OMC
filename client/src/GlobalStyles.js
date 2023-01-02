import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

const GlobalStyles = createGlobalStyle`

  ${reset}
	// 아래에 전역 스타일을 추가
	a {
    text-decoration : none;
    color : inherit;
  }
	body {
  
	}
	`;

export default GlobalStyles;
