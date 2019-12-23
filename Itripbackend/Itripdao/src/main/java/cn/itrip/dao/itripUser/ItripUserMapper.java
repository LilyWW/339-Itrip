package cn.itrip.dao.itripUser;
import cn.itrip.pojo.ItripUser;
import cn.itrip.vo.ItripUserVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripUserMapper {

	//注册功能
	public int loginInsert(ItripUserVO userVO);

	//短信验证
	public Integer updateStatus(@Param("code") String code)throws Exception;


	public ItripUser getItripUserById(@Param(value = "id") Long id)throws Exception;

	public List<ItripUser>	getItripUserListByMap(Map<String, Object> param)throws Exception;

	public Integer getItripUserCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertItripUser(ItripUser itripUser)throws Exception;

	public Integer updateItripUser(ItripUser itripUser)throws Exception;

	public Integer deleteItripUserById(@Param(value = "id") Long id)throws Exception;

	//登录
	public ItripUser login(Map<String, Object> param)throws Exception;

}
