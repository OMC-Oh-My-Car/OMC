// import React from 'react';
import { ModalArea } from './Modal.style';
import { useSelector } from 'react-redux';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import ContentModal from './ContentModal';
import FacilityModal from './FacilityModal';
import ReviewModal from './ReviewModal';
import ImageModal from './ImageModal';
import ReservationInfoModal from './ReservationInfoModal';

const Modal = ({ closeModalController }) => {
  const type = useSelector((state) => state.modal.type);
  const width = useSelector((state) => state.modal.width);
  const height = useSelector((state) => state.modal.height);
  console.log(type);
  const SetContent = () => {
    if (type === 'content') return <ContentModal />;
    else if (type === 'facility') return <FacilityModal />;
    else if (type === 'review') return <ReviewModal />;
    else if (type === 'image') return <ImageModal />;
    else if (type === 'reservationInfo') return <ReservationInfoModal />;
  };

  return (
    <>
      <ModalArea type={type} width={width} height={height}>
        <div className="modalInner">
          <FontAwesomeIcon className="closeIcon" icon={faXmark} onClick={closeModalController} />
          <SetContent />
        </div>
      </ModalArea>
    </>
  );
};

export default Modal;
