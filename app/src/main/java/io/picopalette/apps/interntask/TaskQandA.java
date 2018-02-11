package io.picopalette.apps.interntask;

/**
 * Created by Aswin Sundar on 10-02-2018.
 */

public class TaskQandA {
    private String ques;
    private String ans;

    public TaskQandA(String q,String a)
    {
        ques=q;
        ans=a;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }


}
