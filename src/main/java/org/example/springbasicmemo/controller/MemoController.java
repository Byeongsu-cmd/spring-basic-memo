package org.example.springbasicmemo.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbasicmemo.dto.MemoRequest;
import org.example.springbasicmemo.dto.MemoResponse;
import org.example.springbasicmemo.service.MemoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public MemoResponse saveMemo(
            @RequestBody MemoRequest memoRequest
    ) {
        return memoService.save(memoRequest);
    }

    @GetMapping("/memos")
    public ResponseEntity<List<MemoResponse>> findAllMemos() {
        return ResponseEntity.ok(memoService.memeAll());
    }

    @GetMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponse> findMemoById(
            @PathVariable Long memoId
    ) {
        return ResponseEntity.ok(memoService.findMemoById(memoId));
    }

    @PutMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponse> updateMemo(
            @PathVariable Long memoId,
            @RequestBody MemoRequest memoRequest
    ) {
        return ResponseEntity.ok(memoService.updateMemo(memoId, memoRequest));
    }

    @DeleteMapping("/memos/{memoId}")
    public void deleteMemo(
            @PathVariable Long memoId
    ) {
        memoService.deleteMemo(memoId);
    }
}
