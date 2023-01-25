import { useState } from 'react';
import { SellerProductAddImageArea } from './SellerProductAddImage.style';
import upload from '../../assets/images/imageUpload.png';

const SellerProductAddImage = () => {
  const [showImages, setShowImages] = useState([]);

  const handleAddImages = (event) => {
    const imageLists = event.target.files;
    let imageUrlLists = [...showImages];

    for (let i = 0; i < imageLists.length; i++) {
      const currentImageUrl = URL.createObjectURL(imageLists[i]);
      imageUrlLists.push(currentImageUrl);
    }

    if (imageUrlLists.length > 10) {
      imageUrlLists = imageUrlLists.slice(0, 10);
    }
    console.log(imageUrlLists);
    setShowImages(imageUrlLists);
  };

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
