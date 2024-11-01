import { createGlobalStyle } from 'styled-components';
import NanumSquareLwoff from '../assets/fonts/NanumSquareL.woff';
import NanumSquareLotf from '../assets/fonts/NanumSquareL.otf';
import NanumSquareRwoff from '../assets/fonts/NanumSquareR.woff';
import NanumSquareRotf from '../assets/fonts/NanumSquareR.otf';
import NanumSquareBwoff from '../assets/fonts/NanumSquareB.woff';
import NanumSquareBotf from '../assets/fonts/NanumSquareB.otf';
import NanumSquareEBwoff from '../assets/fonts/NanumSquareEB.woff';
import NanumSquareEBotf from '../assets/fonts/NanumSquareEB.otf';

const GlobalStyle = createGlobalStyle`
    @font-face {
        font-family: 'NSLight';
        src: url(${NanumSquareLwoff}) format('woff'), url(${NanumSquareLotf}) format('opentype');
        font-style: normal;
    }

    @font-face {
        font-family: 'NSRegular';
        src: url(${NanumSquareRwoff}) format('woff'), url(${NanumSquareRotf}) format('opentype');
        font-style: normal;
    }

    @font-face {
        font-family: 'NSBold';
        src: url(${NanumSquareBwoff}) format('woff'), url(${NanumSquareBotf}) format('opentype');
        font-style: normal;
    }

    @font-face {
        font-family: 'NSExtraBold';
        src: url(${NanumSquareEBwoff}) format('woff'), url(${NanumSquareEBotf}) format('opentype');
        font-style: normal;
    }

    body{
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        /* min-width: 1200px; */
        
    }
    html{
        box-sizing: border-box;
    }
`;

export default GlobalStyle;
