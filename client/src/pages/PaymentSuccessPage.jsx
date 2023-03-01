import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Container, MainContainer } from './PaymentSuccessPage.style';
import Header from '../components/header/Header';
import Loading from '../components/loading/Loading';
import { addUserReservation } from '../modules/userReservation/userReservation';

const PaymentSuccessPage = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const orderId = new URL(window.location.href).searchParams.get('orderId');
    const paymentKey = new URL(window.location.href).searchParams.get('paymentKey');
    const amount = new URL(window.location.href).searchParams.get('amount');

    let paymentData = {
      orderId: orderId,
      paymentKey: paymentKey,
      amount: amount,
    };
    const reservationData = JSON.parse(sessionStorage.getItem('reservation'));
    addUserReservation(paymentData, reservationData).then((res) => {
      console.log(res);
    });
    // return () => sessionStorage.removeItem('reservation');
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
