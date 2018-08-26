package com.jyt.demo.jwtdemo.Package;

import com.jyt.demo.jwtdemo.VO.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg("success");
        resultVO.setReturnCode(0);
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer errorCode, String errorMsg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg(errorMsg);
        resultVO.setReturnCode(errorCode);
        return resultVO;
    }
}
