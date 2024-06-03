package org.ul88.error;

public enum ErrorCode {
    SUCCESS, FAIL_SOLD_OUT, FAIL_NO_MONEY, FAIL_MAX_MONEY, FAIL_MANY_PAPER, FAIL
    /*
    SUCCESS : 성공
    FAIL_SOLD_OUT : 음료를 다써서 구매에 실패
    FAIL_NO_MONEY : 음료를 구매할 돈이 없어서 구매에 실패
    FAIL_MAX_MONEY : 돈을 최대치 즉, 7000원을 초과해서 넣으려고 해서 금액 투입에 실패
    FAIL_MANY_PAPER : 지폐를 5개 초과하여 투입하려고 해서 금액 투입에 실패
    FAIL : 그외의 모든 오류
     */
}
