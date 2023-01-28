import { SellerProductAddImageArea } from './SellerProductAddImage.style';
import upload from '../../assets/images/imageUpload.png';

const SellerProductAddImage = ({ showImages, handleAddImages }) => {
  return (
    <>
      <SellerProductAddImageArea>
        <h2>사진 추가하기</h2>
        <div className="imageViewer">
          <label className="uploadLabel" htmlFor="imageUpload">
            <img src={upload} alt="upload" />
          </label>
          <input onChange={handleAddImages} id="imageUpload" type="file" multiple className="imageUpload" />
        </div>
        {showImages.length !== 0 && (
          <>
            <div className="imageSlice">
              {showImages.map((el, index) => {
                return <img key={index} src={el} alt="" />;
              })}
            </div>
          </>
        )}
      </SellerProductAddImageArea>
    </>
  );
};

export default SellerProductAddImage;
