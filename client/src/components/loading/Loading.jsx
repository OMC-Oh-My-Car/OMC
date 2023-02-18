import Spinner from '../../assets/images/Spinner-1s-200px.gif';
import { Container } from './Loading.Style';

const Loading = () => {
  return (
    <>
      <Container>
        <img src={Spinner} alt="" />
      </Container>
    </>
  );
};

export default Loading;
