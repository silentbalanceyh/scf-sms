package com.kingxunlian.infix.sms.mobile;

import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;
import com.fasterxml.jackson.core.JsonObject;
import com.kingxunlian.infix.sms.Sender;
import com.kingxunlian.infix.sms.Tpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UmpSender implements Sender {

    private transient final Tpl tpl;
    private transient final MTPack pack;
    private transient final String NAME = this.getClass().getSimpleName();

    public UmpSender(final Tpl tpl) {
        this.tpl = tpl;
        this.pack = this.getPack();
    }

    @Override
    public boolean send(final Set<String> to, final JsonObject params) {
        return this.sendMsg(this.buildMessage(to, params));
    }

    @Override
    public boolean send(final String to, final JsonObject params) {
        return this.sendMsg(this.buildMessage(to, params));
    }

    private boolean sendMsg(final List<MessageData> msgs) {
        if (0 < msgs.size()) {
            // 设置消息
            this.pack.setMsgs(msgs);
            // 发送消息
            try {
                final PostMsg pm = UmpInitor.getManager();
                final Account account = UmpInitor.getAccount();
                final GsmsResponse response = pm.post(account, this.pack);
                return null != response &&
                        Etat.RC_SUCCESS == response.getResult();
            } catch (final Exception ex) {
                ex.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    private List<MessageData> buildMessage(final Set<String> tos,
                                           final JsonObject params) {
        final List<MessageData> msgs = new ArrayList<>();
        final String content = this.tpl.to(params);
        for (final String to : tos) {
            msgs.add(new MessageData(to, content));
        }
        return msgs;
    }

    private List<MessageData> buildMessage(final String to, final JsonObject params) {
        final String content = this.tpl.to(params);
        return new ArrayList<MessageData>() {
            {
                this.add(new MessageData(to, content));
            }
        };
    }

    private MTPack getPack() {
        final MTPack pack = new MTPack();
        // 批次
        pack.setBatchID(UUID.randomUUID());
        pack.setBatchName(this.tpl.batch());
        // 基本配置
        pack.setMsgType(MTPack.MsgType.SMS);
        pack.setBizType(1);             // 业务类型可根据自己的配置，如果不设置用默认的
        pack.setDistinctFlag(false);    // 是否过滤重复代码
        // 必须包含的信息，否则NullPointer
        pack.setSendType(MTPack.SendType.MASS);
        return pack;
    }
}
