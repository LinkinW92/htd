//package com.skeqi.mes.common.lcy;
//
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.skeqi.mes.pojo.CMesCrudT;
//import com.skeqi.mes.pojo.CMesMenuT;
//import com.skeqi.mes.pojo.CMesUserT;
//import com.skeqi.mes.service.lcy.GetUserService;
//
//public class GetRealm extends AuthorizingRealm {
//	@Autowired
//	private GetUserService us;
//
//
//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection princ) {
//		String username = (String) princ.getPrimaryPrincipal();
//		CMesUserT user=us.getLoginValue(username);
//		Subject subject = SecurityUtils.getSubject();
//		subject.getSession().setAttribute("user", user);
//		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//		//鏉冮檺
//		Set<String> munuName = new HashSet<>();
//		Set<String> roleName = new HashSet<>();
//		Set<String> crudName = new HashSet<>();
//		if("SKQ".equals(username)){
//			if(user!=null){
//				roleName.add("dc0f0c0a976e0830e449060bb23003a3");
//				roleName.add("320c4c7d4988570a6bf264567d47fbad");//宸ヨ壓璁剧疆
//				roleName.add("7bea0e91873e4448c861b9c1eec16e0d");//鐗╂枡閰嶇疆
//				roleName.add("25da462fc5d2907a8c41f14a76f855c3");//璁惧绠＄悊
//				roleName.add("085336140baeac4a7b778b44ddbec9d0");//Bom绠＄悊
//				roleName.add("b9fac29821e13f34c8239d1b3060b61d");//閰嶆柟閰嶇疆
//				roleName.add("83ce82b30ebe95f4b2aabd5a2d4c1489");//璐ㄩ噺閰嶇疆
//				roleName.add("295e68f31d1a4ece055a9604a9ab0722");//璁″垝绠＄悊
//				roleName.add("f37ba5ab1269d2b86ac72d829a744969");///鐢熶骇绠＄悊
//				roleName.add("59d1a23ae69315cf07a081248ac55be6");//鎶ヨ〃绠＄悊
//				roleName.add("d55fea4c16c7b107fc305df1e9927da0");///宸ュ叿
//				roleName.add("5ac2fc6ad71b3a0d10ba758034246379");//鐢ㄦ埛绠＄悊
//				roleName.add("99719ac7e7224221a82b007a8807f41d");//瑙掕壊绠＄悊
//				roleName.add("4812fe52c03d7db7dd6ea073ff8c924a");///宸ヤ綅绠＄悊
//				roleName.add("c563aeb2e511d9b08843d0d5a8cae9e6");//浜х嚎绠＄悊
//				roleName.add("5040f15391e2434cf5478557b50abc9e");//绋嬪簭娉ㄥ唽
//				roleName.add("077a0ebca64c7760ec35610c7f2e37a8");//杞藉叿鏍煎紡绠＄悊
//				roleName.add("5f8684b03da7625066ead94527dbeafc");//浜у搧缁翠慨
//				roleName.add("855571defd4c9b9418ac44c19a6ac0c0");//宸ヨ壓璺嚎
//				roleName.add("57d591033197c49cfa76dd1a52c68ae1");//鐗╂枡绠＄悊
//				roleName.add("b0990d0cb7a53e30a5ca9680f875a645");//鐗╂枡绫诲瀷绠＄悊
//				roleName.add("be12b34b62baee8c658740a6407e7b15");//宸ヨ壓閰嶇疆
//				roleName.add("284e45831e4dfddd5b38395d1783de03");//鏂欏崟绠＄悊
//				roleName.add("f2655b3d30b7dd6f2333938d754d0f6c");//鍒堕�犲弬鏁版竻鍗曠鐞�
//				roleName.add("1608d1abe23abc1c1389fec3831d4beb");//鎬绘垚绫诲瀷绠＄悊
//				roleName.add("b51ede8f11d2e850ac31a689416f740b");///鎶ヨ淇℃伅绠＄悊
//				roleName.add("67d326f49a5589120496f9be1e840951");///璁惧绠＄悊
//				roleName.add("79dd0cb255215e222d2737e8a1dbb704");//璁惧鍖哄煙淇℃伅
//				roleName.add("5d29de8e5600368955db850bc826745f");//Bom绠＄悊
//				roleName.add("27efe1492adcaa5659230d609daceb99");//Bom鏄庣粏
//				roleName.add("96f3f9ed55411dda04d919f2d40bf504");//鐗╂枡淇℃伅
//				roleName.add("eaf5cd991dd2d41c582c455785d4a8b7");//铻烘爴淇℃伅
//				roleName.add("a5dbd8b698c4b1707a70e93643898c02");///姘斿瘑鎬т俊鎭�
//				roleName.add("20702b349c2fce9f6dc07b66cc056f68");//鍏朵粬淇℃伅
//				roleName.add("770dfb175079a2819fcd48b40b3b6d29");//閰嶆柟绠＄悊
//				roleName.add("d028caffddf868a64b987d2d539ead26");//閰嶆柟鏄庣粏绠＄悊
//				roleName.add("3ce975d8750ca34488a277b85dc3f1c3");///浜у搧绠＄悊
//				roleName.add("0af5628a77bb8dc5fa2387a26d7752b7");//缂洪櫡绠＄悊
//				roleName.add("47169d004585c554f00b6f81ee1549a4");///缂洪櫡绛夌骇绠＄悊
//				roleName.add("01c9879cfc659208da9fec8ec99daa28");//鍘熷洜绠＄悊
//				roleName.add("64b2b74226d69f68c4a05c72a6f5b543");//璐ｄ换绠＄悊
//				roleName.add("f0dae89d35ba67c08a83b129462ad880");//璐ｄ换绫诲瀷绠＄悊
//				roleName.add("be1c73d568f1b5c58b90b6524068f241");//璁″垝閰嶇疆
//				roleName.add("af3c1b18dbfa295c00461836e0bab5a2");//宸插畬鎴愬伐鍗�
//				roleName.add("b803109b88ad096af1c2a6b703104dc9");//寮哄埗鍏抽棴宸ュ崟
//				roleName.add("79f5364345dfd049a6e1f18a5bb2de3f");///鐢熸垚鏉＄爜
//				roleName.add("e80b4574afd6186efc2b302f35150586");//鐝绠＄悊
//				roleName.add("ba34e5dac02e9253c5bdbc2c46e82060");//鐝粍绠＄悊
//				roleName.add("bb46675488d6a71d04f26a7de71ef057");//閫氱煡绠＄悊
//				roleName.add("43466da8d26a68967db279db8f78e712");//鍛樺伐绠＄悊
//				roleName.add("5f76b646e192690d06a48daea348aabf");//鐢靛瓙鎶ヨ〃
//				roleName.add("227748a33327e4a89c2079d62d8283ba");///鏁版嵁鎶ヨ〃
//				roleName.add("7255d54c8491f991fd8d57c456427ea4");//鏈堜骇閲忕粺璁�
//				roleName.add("2b1156d38fad79089a934ab15df44c1f");///鏃ヤ笅绾跨粺璁�
//				roleName.add("efc66a90d4502a7a7cb2d8f5597fddc2");///鏈堝悎鏍奸噺缁熻
//				roleName.add("0b0838f18a9985632f50fb5c261d91c8");///鏈堟皵瀵嗘�ф祴璇曠粺璁�
//				roleName.add("3bfbb200406106d2c7516cf1fbd00b38");///鏃ヤ骇閲忕粺璁�
//				roleName.add("5e0d1a59bd9558941688e0cda2759541");//璁惧浣跨敤鐜�
//				roleName.add("4f0004a18b663b016215b40dc05d029f");//璁惧OEE
//				roleName.add("a4ac5a2dced9acf81187ca5ce1463a91");//鎷х揣鍚堟牸鐜�
//				roleName.add("b0b9eab214e6185db3f540b2d2b5442a");//宸ヤ綅瀹屾垚鏁伴噺缁熻
//				roleName.add("2b430959e35154476b9764d9d193a903");//鐝鏁伴噺缁熻
//				roleName.add("0fb2bf382910c4253edd4080cf064afb");//鏁翠綋鍚堟牸鐜囩粺璁�
//				roleName.add("a3072d6b350c85bc67ee6ecf5a4f4cc7");//宸ヤ綅鏃堕棿缁熻
//				roleName.add("f6413c0b65d0ead3856ecf285e3f0c90");//涓�娆￠�氳繃鐜�
//				roleName.add("655ad688bb509e1fd17b1062baba5af0");//搴熷搧鐜囩粺璁�
//				roleName.add("4d942c949c8119bc1a9b6855d30a10a6");//瀵煎叆閰嶆柟
//				roleName.add("1f3b91755512854f53cf6cb329061a35");//鎵嬪姩涓婁紶
//				roleName.add("a22f163273ccc1d4a58241b17e520ba9");//鏍囩绠＄悊
//				roleName.add("409184874edc189a0146fb46c6501b98");//瑙勫垯绫诲瀷绠＄悊
//				roleName.add("afe4666f95bfccb95696c58e0c20961b");//瑙勫垯绫诲瀷绠＄悊
//				roleName.add("e0dc1bd554a05d137169e35e51a301a2");//设备状态监控
//				roleName.add("58da71c3ebaf07429b9e3342d8169869");//重工
//				munuName.add("BasicSetting");
//				munuName.add("dataDismantling");
//				munuName.add("roleManager");
//				munuName.add("roleManagerAddSomeData");
//				munuName.add("roleManagerUpdateSomeData");
//				munuName.add("roleManagerDeleteSomeData");
//				munuName.add("roleManagerOperation");
//				munuName.add("userListAddSomeData");
//				munuName.add("userListUpdateSomeData");
//				munuName.add("userListDeleteSomeData");
//				munuName.add("userListOperation");
//				String[] getMenuT = us.getMenuT(); //所有模块
//				for (int i = 0; i < getMenuT.length; i++) {
//					munuName.add(getMenuT[i]);
//					String[] crudNames = us.getCrudName();  //增删改  5
//					for (int j = 0; j < crudNames.length; j++) {
//						crudName.add(getMenuT[i]+crudNames[j]);
//					}
//					crudName.add(getMenuT[i]+"Operation");
//				}
//				info.setRoles(roleName);
//				munuName.addAll(crudName);
//				info.setStringPermissions(munuName);
//			}
//		}else{
//			if(user!=null){
//
//				String[] strs = us.getUserPowerT();  //查询所有模块的加密url
//
//				for (int i = 0; i < strs.length; i++) {
//					roleName.add(strs[i]);
//				}
//
//
//				//璁剧疆鏉冮檺
//				Set<CMesMenuT> setMenu = user.getSetMenu();  //查询该用户下的模块
//				Iterator<CMesMenuT> iterMenu = setMenu.iterator();
//
//				while(iterMenu.hasNext()){
//					CMesMenuT menuT = iterMenu.next();
//					if(menuT.getUrl()!=null&&menuT.getUrl()!=""){
//						munuName.add(menuT.getUrl());   //模块的url(未加密)
//						if(user.getRoleId()!=null&&menuT.getId()!=null){
//							List<CMesCrudT> crudList = us.getCrudList(user.getRoleId(),menuT.getId());  //增删改
//							if(crudList!=null&&crudList.size()!=0){
//								for (int i = 0; i < crudList.size(); i++) {
//									if(crudList.get(i)!=null){
//										if(crudList.get(i).getCrudAlias()!=null&&crudList.get(i).getCrudAlias()!=""){
//											crudName.add(menuT.getUrl()+crudList.get(i).getCrudAlias());
//											if("UpdateSomeData".equals(crudList.get(i).getCrudAlias())
//													||"DeleteSomeData".equals(crudList.get(i).getCrudAlias())){
//												crudName.add(menuT.getUrl()+"Operation");
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//
//				}
//
//				munuName.addAll(crudName);
//				Iterator<String> it = munuName.iterator();
////				while(it.hasNext()){
////					System.out.println(it.next());
////				}
//				info.setRoles(roleName);
//				info.setStringPermissions(munuName);
//			}
//		}
//		return info;
//	}
//
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//
//		String username=(String)token.getPrincipal();//鑾峰彇涓汉淇℃伅锛�
//
//		CMesUserT user=us.getLoginValue(username);
//
//		SimpleAuthenticationInfo info = null;
//		if(user!=null){//璇存槑鏌ュ埌浜�
//			info = new SimpleAuthenticationInfo(user.getUserName(),user.getUserPwd(),getName());
//		}
//		return info;
//
//	}
//
//}
