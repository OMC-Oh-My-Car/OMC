import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Container, MainContainer } from './PaymentSuccessPage.style';
import Header from '../components/header/Header';
import Loading from '../components/loading/Loading';

const PaymentFailedPage = () => {
  const navigate = useNavigate();

  useEffect(() => {
    window.alert('결제 실패.');
    navigate('/');
  }, []);

  return (
    <>
      <Container>
        <Header type="long" />
        <MainContainer>
          <Loading />
        </MainContainer>
      </Container>
    </>
  );
};

export default PaymentFailedPage;
