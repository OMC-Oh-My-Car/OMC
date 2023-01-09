import { WhiteButtonStyle } from './WhiteButton.style';

const WhiteButton = ({ width, height, text }) => {
  return (
    <>
      <WhiteButtonStyle width={width} height={height}>
        {text}
      </WhiteButtonStyle>
    </>
  );
};
export default WhiteButton;
