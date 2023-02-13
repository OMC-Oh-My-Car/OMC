import { useState, useEffect } from 'react';
import { Container, MainContainer } from './SellerProductEditPage.style';
import Header from '../components/header/Header';
import SellerProductAddImage from '../components/sellerProductAdd/SellerProductAddImage';
import SellerProductAddTitle from '../components/sellerProductAdd/SellerProductAddTitle';
import SellerProductAddContent from '../components/sellerProductAdd/SellerProductAddContent';
import SellerProductAddPostCode from '../components/sellerProductAdd/SellerProductAddPostCode';
import SellerProductAddPrice from '../components/sellerProductAdd/SellerProductAddPrice';
import SellerProductAddTag from '../components/sellerProductAdd/SellerProductAddTag';
import { useDaumPostcodePopup } from 'react-daum-postcode';
import { useNavigate, useParams } from 'react-router-dom';
import { useMutation, useQuery } from 'react-query';
import { editProduct, getProductInfo } from '../modules/sellerProduct/sellerProductEdit';

const SellerProductEditPage = () => {
  const params = useParams();
  const navigate = useNavigate();
  let productId = params.productId;
  console.log(productId);

  const mutation = useMutation(() => editProduct(content, productId), {
    onMutate() {},
    onSuccess(data) {
      console.log(data);
    },
    onError(err) {
      console.log(err);
    },
  });
  const onSuccess = (data) => {
    // 데이터 useState에 담기
    console.log(data);
  };
  if (productId) {
    const { data } = useQuery(
      ['sellerProductInfo', productId],
      async () => {
        const data = await getProductInfo(productId);
        return data;
      },
      {
        onSuccess,
      },
    );
  }

  const [showImages, setShowImages] = useState([]);
  const [image, setImage] = useState([]);
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [address, setAddress] = useState('');
  const [zipCode, setZipCode] = useState('');
  const [price, setPrice] = useState('');
  const [tags, setTags] = useState([]);
  const [tagContent, setTagContent] = useState('');

  // 이미지 추가
  const handleAddImages = (event) => {
    const imageLists = event.target.files;
    let preImage = [...image];
    let imageUrlLists = [...showImages];

    for (let i = 0; i < imageLists.length; i++) {
      const currentImageUrl = URL.createObjectURL(imageLists[i]);
      imageUrlLists.push(currentImageUrl);
      preImage.push(imageLists[i]);
    }

    if (imageUrlLists.length > 10) {
      imageUrlLists = imageUrlLists.slice(0, 10);
      preImage = preImage.slice(0, 10);
    }

    setShowImages(imageUrlLists);
    setImage(preImage);
  };

  // 이미지 삭제
  const handleDeleteImage = (index) => {
    let showImagesTemp = [...showImages];
    let imageTemp = [...image];
    showImagesTemp.splice(index, 1);
    imageTemp.splice(index, 1);
    setShowImages(showImagesTemp);
    setImage(imageTemp);
  };

  // 제목
  const handleTitle = (event) => {
    setTitle(event.target.value);
  };

  // 컨텐츠
  const handleContent = (event) => {
    setContent(event.target.value);
  };

  //주소
  const open = useDaumPostcodePopup();

  const addressPostHandler = (data) => {
    let fullAddress = data.address;
    let extraAddress = '';

    if (data.addressType === 'R') {
      if (data.bname !== '') {
        extraAddress += data.bname;
      }
      if (data.buildingName !== '') {
        extraAddress += extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
    }
    setAddress(fullAddress);
    setZipCode(data.zonecode);
  };

  const postCodeHandler = (e) => {
    e.preventDefault();
    open({ onComplete: addressPostHandler });
  };

  // 상품 가격
  const handlePrice = (event) => {
    setPrice(event.target.value);
  };

  // 태그 삭제
  const removeTags = (indexToRemove) => {
    // TODO : 태그를 삭제하는 메소드를 완성하세요.
    let deleteTags = [...tags];
    deleteTags.splice(indexToRemove, 1);
    setTags([...deleteTags]);
  };

  // 태그 추가
  const addTags = (event) => {
    if (!(event.target.value === '') && !tags.includes(event.target.value)) {
      setTags([...tags, event.target.value]);
      event.target.value = '';
      setTagContent('');
    }
  };
  // 뒤로가기
  const navBack = () => {
    navigate(`/seller/12/product`);
  };
  // 상품 수정
  const editProduct = (item) => {
    console.log(item);
    mutation.mutate(item);
    // navigate(`/seller/12/product`);
  };

  return (
    <>
      <Container>
        <Header type="short" />
        <MainContainer>
          <h1>상품 등록</h1>
          <SellerProductAddImage
            showImages={showImages}
            handleAddImages={handleAddImages}
            handleDeleteImage={handleDeleteImage}
          />
          <SellerProductAddTitle title={title} handleTitle={handleTitle} />
          <SellerProductAddContent content={content} handleContent={handleContent} />
          <SellerProductAddPostCode address={address} zipCode={zipCode} postCodeHandler={postCodeHandler} />
          <SellerProductAddPrice price={price} handlePrice={handlePrice} />
          <SellerProductAddTag
            tags={tags}
            addTags={addTags}
            removeTags={removeTags}
            tagContent={tagContent}
            setTagContent={setTagContent}
          />
          <div className="button">
            <button onClick={() => navBack()} className="red">
              취소하기
            </button>
            <button
              onClick={() =>
                editProduct({
                  subject: title,
                  description: content,
                  address: address,
                  zipcode: zipCode,
                  facilities: tags,
                  telephone: '02-123-1234',
                  price: price,
                  checkIn: '13:00',
                  checkOut: '11:00',
                })
              }
            >
              수정하기
            </button>
          </div>
        </MainContainer>
      </Container>
    </>
  );
};
export default SellerProductEditPage;
