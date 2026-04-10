/**
 * Mock Manga Database for Frontend Demo
 * Simulates data that would typically come from a Backend API/Database
 */
const mangaDatabase = {
    "one-piece": {
        title: "One Piece - Đảo Hải Tặc",
        author: "Oda Eiichiro",
        chapters: [
            {
                id: "1080",
                title: "Anh Hùng Tái Xuất",
                images: [
                    "https://placehold.co/900x1200/1e293b/ffffff?text=Page+1+Chương+1080",
                    "https://placehold.co/900x1200/334155/ffffff?text=Page+2+Chương+1080",
                    "https://placehold.co/900x1200/1e293b/ffffff?text=Page+3+Chương+1080",
                    "https://placehold.co/900x1200/334155/ffffff?text=Page+4+Chương+1080"
                ]
            },
            {
                id: "1081",
                title: "Thuyền Trưởng Thập Nhị Thuyền",
                images: [
                    "https://placehold.co/900x1200/474b4e/ffffff?text=Page+1+Chương+1081",
                    "https://placehold.co/900x1200/2c3e50/ffffff?text=Page+2+Chương+1081",
                    "https://placehold.co/900x1200/474b4e/ffffff?text=Page+3+Chương+1081",
                    "https://placehold.co/900x1200/2c3e50/ffffff?text=Page+4+Chương+1081",
                    "https://placehold.co/900x1200/474b4e/ffffff?text=Page+5+Chương+1081"
                ]
            },
            {
                id: "1082",
                title: "Mọi Thứ Đều Vì Công Lý",
                images: [
                    "https://placehold.co/900x1200/0f172a/ffffff?text=Page+1+Chương+1082",
                    "https://placehold.co/900x1200/1e293b/ffffff?text=Page+2+Chương+1082",
                    "https://placehold.co/900x1200/0f172a/ffffff?text=Page+3+Chương+1082"
                ]
            }
        ]
    }
};

window.mangaDatabase = mangaDatabase;
