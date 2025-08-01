package org.example.springbasicmemo.service;

import lombok.RequiredArgsConstructor;
import org.example.springbasicmemo.dto.MemoRequest;
import org.example.springbasicmemo.dto.MemoResponse;
import org.example.springbasicmemo.entity.Memo;
import org.example.springbasicmemo.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponse save(MemoRequest memoRequest) {
        Memo memo = new Memo(memoRequest.getTitle(), memoRequest.getContent());
        Memo memoSaved = memoRepository.save(memo);

        return new MemoResponse(
                memoSaved.getId(),
                memoSaved.getTitle(),
                memoSaved.getContent(),
                memoSaved.getCreatedAt(),
                memoSaved.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<MemoResponse> memeAll() {
        List<Memo> memos = memoRepository.findAll();
        return memos.stream()
                .map(memo -> new MemoResponse(
                        memo.getId(),
                        memo.getTitle(),
                        memo.getContent(),
                        memo.getCreatedAt(),
                        memo.getModifiedAt()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public MemoResponse findMemoById(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("입력하신 id" + id + "를 찾을 수 없습니다.")
        );
        return new MemoResponse(
                memo.getId(),
                memo.getTitle(),
                memo.getContent(),
                memo.getCreatedAt(),
                memo.getModifiedAt()
        );
    }

    @Transactional
    public MemoResponse updateMemo(Long id, MemoRequest memoRequest) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("입력하신 id" + id + "를 찾을 수 없습니다.")
        );
        memo.updateMemo(memoRequest.getTitle(), memoRequest.getContent());
        return new MemoResponse(
                memo.getId(),
                memo.getTitle(),
                memo.getContent(),
                memo.getCreatedAt(),
                memo.getModifiedAt()
        );
    }

    @Transactional
    public void deleteMemo(Long id) {
        boolean exists = memoRepository.existsById(id);
        if (!exists) {
            throw new IllegalArgumentException("입력하신 id" + id + "를 찾을 수 없습니다.");
        }
        memoRepository.deleteById(id);
    }
}
