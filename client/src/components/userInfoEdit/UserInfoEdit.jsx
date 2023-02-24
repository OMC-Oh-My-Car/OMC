import { Template, UserInputButton } from './UserInfoEdit.style';
import { useQuery, useMutation } from 'react-query';
import { useState } from 'react';
import ProfileImg from './ProfileImg';
import { getUserInfo, editUserInfo } from '../../modules/member/userInfo';
import UserInfoInput from './UserInfoInput';

const UserInfoEdit = () => {
  const [showImages, setShowImages] = useState([]);
  const [image, setImage] = useState([]);
  const [email, setEmail] = useState('');
  const [nickname, setNickname] = useState('');
  const [username, setUsername] = useState('');
  const [phone, setPhone] = useState('');

  // 이미지
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

  const { isLoading, data, isError } = useQuery(
    ['userInfo'],
    async () => {
      const data = await getUserInfo();
      return data;
    },
    {
      onSuccess(data) {
        setEmail(data.data.email);
        setNickname(data.data.nickname);
        setUsername(data.data.username);
        setPhone(data.data.phone);
      },
    },
  );
  const mutation = useMutation(
    () =>
      editUserInfo(image, {
        email,
        nickname,
        username,
        phone,
      }),
    {
      onMutate() {},
      onSuccess(data) {
        console.log(data);
      },
      onError(err) {
        console.log(err);
      },
    },
  );

  return (
    <>
      <Template>
        <div className="userInfoHead">
          <h1>회원정보</h1>
        </div>
        <ProfileImg data={data} handleAddImages={handleAddImages} showImages={showImages} />
        <UserInfoInput
          labelName="Email"
          inputId="email"
          inputType="email"
          name="email"
          value={email}
          placeholder="정보를 추가해주세요"
          disabled={true}
        />
        <UserInfoInput
          labelName="nickname"
          inputId="nickname"
          inputType="text"
          name="nickname"
          value={nickname}
          placeholder="정보를 추가해주세요"
          disabled={true}
        />
        <UserInfoInput
          labelName="이름"
          inputId="username"
          inputType="text"
          name="username"
          value={username}
          placeholder="정보를 추가해주세요"
          p="실명으로 기입하지 않는 경우 배송 및 현장수령 시 문제가 발생할 수 있습니다."
          disabled={true}
        />
        <UserInfoInput
          labelName="휴대폰번호"
          inputId="phone"
          inputType="text"
          name="phone"
          value={phone}
          placeholder="정보를 추가해주세요"
          p="정확한 번호가 아닐 경우 배송 및 현장수령 시 문제가 발생할 수 있습니다."
          disabled={true}
        />

        <UserInputButton className="red" onClick={() => mutation.mutate()}>
          정보 수정
        </UserInputButton>
        <UserInputButton onClick={() => mutation.mutate()}>취소</UserInputButton>
      </Template>
    </>
  );
};
export default UserInfoEdit;
