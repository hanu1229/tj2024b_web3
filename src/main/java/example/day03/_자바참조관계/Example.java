package example.day03._자바참조관계;

import java.util.ArrayList;

public class Example {
    public static void main(String[] args) {
        // 참조 : 실제 인스턴스(객체)의 위치(주소)
        // 1. 인스턴스 생성
        // 2. 생성된 인스턴스를 변수가 참조
        Category category = new Category();
        // 참조 안에 참조 안에 참조 안에 참조 .(도트연산자)
        category.set카테고리번호(1);
        category.set카테고리명("공지사항");
        // 변수가 참조하는 인스턴스에는 2개의 멤버변수를 참조할 수 있다.
        System.out.println("========== ↓ 카테고리 ↓ ==========");
        System.out.println(category.get카테고리번호());
        System.out.println(category.get카테고리명());
        System.out.println("========== ↑ 카테고리 ↑ ==========");
        // 공지사항 카테고리를 참조하는 게시물 인스턴스 생성
        Board board = Board.builder()
                .게시물번호(1).게시물제목("안녕하세여").게시물내용("게시물 내용")
                .category(category).build();
        // ↑ board변수가 참조한 횟수 : 7
        System.out.println("========== ↓ 게시물 ↓ ==========");
        System.out.println(board.get게시물번호());
        System.out.println(board.get게시물제목());
        System.out.println(board.get게시물내용());
        System.out.println(board.getCategory().get카테고리번호());
        System.out.println(board.getCategory().get카테고리명());
        System.out.println("========== ↑ 게시물 ↑ ==========");
        // 공지사항 카테고리에 1번 게시물에 댓글 작성
        Reply reply = Reply.builder().댓글번호(1).댓글내용("댓글1").board(board).build();
        // ↓ reply변수가 참조한 횟수 : 10
        System.out.println("========== ↓ 댓글 ↓ ==========");
        System.out.println(reply.get댓글번호());
        System.out.println(reply.get댓글내용());
        System.out.println(reply.getBoard().get게시물번호());
        System.out.println(reply.getBoard().get게시물제목());
        System.out.println(reply.getBoard().get게시물내용());
        System.out.println(reply.getBoard().getCategory().get카테고리번호());
        System.out.println(reply.getBoard().getCategory().get카테고리명());
        System.out.println("========== ↑ 댓글 ↑ ==========");

        // [단방향] | 데이터베이스 사용 | PK-FK 관계



        // [양방향]
        // 양방향 대입
        category.setBoardList(new ArrayList<>());
        category.getBoardList().add(board);
        // 양방향 대입
        board.setReplyList(new ArrayList<>());
        board.getReplyList().add(reply);
        System.out.println(category.getBoardList().get(0).getReplyList().get(0));


    }
}
