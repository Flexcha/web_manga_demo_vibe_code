document.addEventListener('DOMContentLoaded', () => {

    // ==========================================
    // LOGIC RÚT CHỮ CÁI ĐẦU TIÊN TỪ TÊN NGƯỜI DÙNG DB
    // ==========================================
    const mockDbUser = {
        fullName: "Dương Minh" 
    };

    const avatarBtn = document.querySelector('.btn-user');
    if (avatarBtn && mockDbUser.fullName) {
        let firstLetter = mockDbUser.fullName.trim().charAt(0).toUpperCase();
        avatarBtn.textContent = firstLetter;
    }

    // ==========================================
    // LOGIC LIVE SEARCH (TÌM KIẾM NHANH)
    // ==========================================
    const btnSearchToggle = document.getElementById('btnSearchToggle');
    const searchOverlay = document.getElementById('searchOverlay');
    const closeSearchBtn = document.getElementById('closeSearchBtn');
    const liveSearchInput = document.getElementById('liveSearchInput');
    const searchResults = document.getElementById('searchResults');
    const searchResultList = document.getElementById('searchResultList');
    const searchAllText = document.getElementById('searchAllText');

    if (btnSearchToggle && searchOverlay) {
        // Bật Overlay
        btnSearchToggle.addEventListener('click', () => {
            searchOverlay.classList.remove('d-none');
            setTimeout(() => liveSearchInput.focus(), 100);
        });

        // Tắt Overlay
        closeSearchBtn.addEventListener('click', () => {
            searchOverlay.classList.add('d-none');
            liveSearchInput.value = '';
            searchResults.classList.add('d-none');
        });

        // Xử lý Gõ phím tìm kiếm (Mock API Call)
        let typingTimer;
        if(liveSearchInput) {
            liveSearchInput.addEventListener('input', (e) => {
                const val = e.target.value.trim();
                clearTimeout(typingTimer);
                
                if (val.length > 0) {
                    typingTimer = setTimeout(() => {
                        if(searchAllText) searchAllText.textContent = `Tìm tất cả kết quả cho từ khóa ` + val;
                        
                        searchResultList.innerHTML = ''; 
                        for (let i = 1; i <= 4; i++) {
                            searchResultList.innerHTML += `
                                <a href="search.html" class="list-group-item list-group-item-action d-flex align-items-center gap-3 p-3 search-result-item border-bottom">
                                    <img src="https://placehold.co/100x140/e0e0e0/616161?text=Cover`+i+`" alt="Cover" class="search-thumb">
                                    <div>
                                        <h6 class="mb-1 fw-bold text-dark fs-6">\${val} Friends Tập \${i}</h6>
                                        <small class="text-muted">Tác giả ngẫu nhiên</small>
                                    </div>
                                </a>
                            `;
                        }
                        searchResults.classList.remove('d-none');
                    }, 300); 
                } else {
                    searchResults.classList.add('d-none');
                }
            });
        }
    }
});
