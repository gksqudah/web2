package kr.icia.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	private Long rno;	//���� ��ȣ
	private Long bno;	//�Խù� ��ȣ
	
	private String reply;	// ���� ����
	private String replyer;	// ���� �ۼ���
	private Date replyDate;	// ���� �ۼ���
	private Date updateDate;	//���� ������
}
