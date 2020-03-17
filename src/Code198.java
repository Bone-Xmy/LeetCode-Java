public class Code198 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[][] a = new int[n][2];

        a[0][0] = 0;
        a[0][1] = nums[0];

        for (int i = 1; i < n; i++) {
            // 一个循环里面计算出偷 i 和 不偷 i 房子后能得到的金额，最后看谁更大
            a[i][0] = Math.max(a[i - 1][0], a[i - 1][1]);
            a[i][1] = a[i - 1][0] + nums[i];
        }
        return Math.max(a[n - 1][0], a[n - 1][1]);
    }

}


/**
 * a[i] : 0...i 能偷到的 max value
 * a[i] = a[i - 1] + nums[i]
 *      这种方式不知道第 i - 1 个房子是否被偷过，由于不能连续偷，所以也就不确定最后一个房子 nums[i] 是否可以偷
 *
 * 需要增加一维表示状态
 * a[i][0,1] : 0: 不偷 1: 偷
 * a[i][0] = Max(a[i - 1][0], a[i - 1][1])
 *      如果第 i 个房子不偷，则最大偷到金额 = 第 i - 1 个房子 偷 或者 不偷 的合计金额最大值
 * a[i][1] = Max(a[i - 1][0],0) + nums[i]  =====都为正数，所以不用max======》 a[i][1] = a[i - 1][0] + nums[i]
 *      如果第 i 个房子偷，则最大偷到金额 = i - 1 房子不偷 加上 第 i 个房子肯定偷后的合计金额
 */
