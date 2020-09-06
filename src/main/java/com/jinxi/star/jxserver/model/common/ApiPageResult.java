package com.jinxi.star.jxserver.model.common;

import com.github.pagehelper.Page;

public class ApiPageResult<T> extends ApiResult {
    private static final long serialVersionUID = 1195989840743309021L;
    private DataResult<T> result;

    public static <T> ApiPageResult<T> succ(Page<T> paging) {
        ApiPageResult<T> result = new ApiPageResult<T>();
        result.setCode(0);
        result.setMsg("成功");
        result.setResult(new DataResult<T>(paging));
        return result;
    }

    public DataResult<T> getResult() {
        return result;
    }

    public void setResult(DataResult<T> result) {
        this.result = result;
    }


}