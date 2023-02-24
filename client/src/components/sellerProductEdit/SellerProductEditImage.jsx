import { SellerProductEditImageArea } from './SellerProductEditImage.style';
import upload from '../../assets/images/imageUpload.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';

const SellerProductEditImage = ({ showImages, handleAddImages, handleDeleteImage, data }) => {
  return (
    <>
      <SellerProductEditImageArea>
        <h2>사진 추가하기</h2>
        <div className="imageViewer">
          <label className="uploadLabel" htmlFor="imageUpload">
            <img src={upload} alt="upload" />
          </label>
          <input onChange={handleAddImages} id="imageUpload" type="file" multiple className="imageUpload" />
        </div>
        {showImages.length !== 0 ? (
          <>
            <div className="imageSlice">
              {showImages.map((el, index) => {
                return (
                  <>
                    <div className="imageArea">
                      <FontAwesomeIcon onClick={() => handleDeleteImage(index)} className="closeIcon" icon={faXmark} />
                      <img key={index} src={el} alt="" />
                    </div>
                  </>
                );
              })}
            </div>
          </>
        ) : (
          <>
            <div className="imageSlice">
              {data &&
                data.data.data.img.map((el, index) => {
                  return (
                    <>
                      <div className="imageArea">
                        <img key={index} src={el} alt="" />
                      </div>
                    </>
                  );
                })}
            </div>
          </>
        )}
      </SellerProductEditImageArea>
    </>
  );
};

export default SellerProductEditImage;
