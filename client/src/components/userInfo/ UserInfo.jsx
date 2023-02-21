import { Template, UserInputButton } from './ UserInfo.style';
import { useQuery, useMutation } from 'react-query';
import { useState } from 'react';
import ProfileImg from './userInfoForm/ProfileImg';
import { getUserInfo, editUserInfo } from '../../modules/member/userInfo';
import UserInfoInput from './userInfoForm/UserInfoInput';

const UserInfo = () => {
  const [user, setUser] = useState({
    email: '',
    username: '',
    profileImg: '',
    phone: '',
  });

  const { isLoading, data, isError } = useQuery(['userInfo'], async () => {
    const data = await getUserInfo();
    return data;
  });
  console.log(data);
  const mutation = useMutation(
    () =>
      editUserInfo({
        email: 'test@gmail.com',
        username: '김철수',
        profileImg: 'http://image.dongascience.com/Photo/2016/09/14750507361195.jpg',
        phone: '010-1234-5678',
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
      <Template key={user.id}>
        <div className="userInfoHead">
          <h1>회원정보</h1>
        </div>
        <ProfileImg data={data} />
        <UserInfoInput
          labelName="Email"
          inputId="email"
          inputType="email"
          name="email"
          value={data ? data.data.email : ''}
          placeholder="정보를 추가해주세요"
          disabled={true}
        />
        <UserInfoInput
          labelName="nickname"
          inputId="nickname"
          inputType="text"
          name="nickname"
          value={data ? data.data.nickname : ''}
          placeholder="정보를 추가해주세요"
          disabled={true}
        />
        <UserInfoInput
          labelName="이름"
          inputId="username"
          inputType="text"
          name="username"
          value={data ? data.data.username : ''}
          placeholder="정보를 추가해주세요"
          p="실명으로 기입하지 않는 경우 배송 및 현장수령 시 문제가 발생할 수 있습니다."
          disabled={true}
        />
        <UserInfoInput
          labelName="휴대폰번호"
          inputId="phone"
          inputType="text"
          name="phone"
          value={data ? data.data.phone : ''}
          placeholder="정보를 추가해주세요"
          p="정확한 번호가 아닐 경우 배송 및 현장수령 시 문제가 발생할 수 있습니다."
          disabled={true}
        />

        <UserInputButton className="red" onClick={() => mutation.mutate()}>
          정보 수정
        </UserInputButton>
        <UserInputButton onClick={() => mutation.mutate()}>회원 탈퇴</UserInputButton>
      </Template>
    </>
  );
};
export default UserInfo;
