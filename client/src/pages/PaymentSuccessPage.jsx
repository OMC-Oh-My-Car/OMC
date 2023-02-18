import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Container, MainContainer } from './PaymentSuccessPage.style';
import Header from '../components/header/Header';
import Loading from '../components/loading/Loading';

const PaymentSuccessPage = () => {
  const navigate = useNavigate();
  useEffect(() => {
    const orderId = new URL(window.location.href).searchParams.get('orderId');
    const paymentKey = new URL(window.location.href).searchParams.get('paymentKey');
    const amount = new URL(window.location.href).searchParams.get('amount');
    let data = {
      orderId: orderId,
      paymentKey: paymentKey,
      amount: amount,
    };
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

export default PaymentSuccessPage;
