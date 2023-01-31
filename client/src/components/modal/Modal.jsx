// import React from 'react';
import { ModalArea } from './Modal.style';
import { useSelector } from 'react-redux';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import ContentModal from './ContentModal';

const Modal = ({ closeModalController }) => {
  const type = useSelector((state) => state.modal.type);
  const width = useSelector((state) => state.modal.width);
  const height = useSelector((state) => state.modal.height);
  const SetContent = () => {
    if (type === 'content') return <ContentModal />;
    // else if (type === 'signUp') return <SignUp />;
  };

  return (
    <>
      <ModalArea width={width} height={height}>
        <div className="modalInner">
          <FontAwesomeIcon className="closeIcon" icon={faXmark} onClick={closeModalController} />
          <SetContent />
        </div>
      </ModalArea>
    </>
  );
};

export default Modal;
