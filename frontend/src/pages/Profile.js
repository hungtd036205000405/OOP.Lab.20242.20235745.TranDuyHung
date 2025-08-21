import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import "./Profile.css";

const Profile = () => {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);
  const [isEditing, setIsEditing] = useState(false);
  
  const [profile, setProfile] = useState({
    userId: localStorage.getItem("userId"),
    username: localStorage.getItem("username"),
    email: localStorage.getItem("email") || "",
    fullName: "",
    phone: "",
    address: "",
    avatar: "https://via.placeholder.com/150",
    membershipLevel: "Silver",
    points: 150,
    totalOrders: 0,
    joinDate: "2025-01-01",
    favoriteCount: 0
  });

  const [editedProfile, setEditedProfile] = useState({});

  useEffect(() => {
    if (!profile.userId) {
      navigate('/login');
      return;
    }

    // Fetch user profile from API
    fetch(`http://localhost:8080/api/users/${profile.userId}`)
      .then(res => res.json())
      .then(data => {
        setProfile(prev => ({
          ...prev,
          ...data,
          membershipLevel: data.membershipLevel || "Silver",
          points: data.points || 0,
          totalOrders: data.totalOrders || 0,
          favoriteCount: data.favoriteCount || 0
        }));
        setLoading(false);
      })
      .catch(err => {
        console.error("Error fetching profile:", err);
        setLoading(false);
      });
  }, [profile.userId, navigate]);

  const handleEdit = () => {
    setIsEditing(true);
    setEditedProfile({ ...profile });
  };

  const handleSave = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/users/${profile.userId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(editedProfile),
      });

      if (response.ok) {
        setProfile(editedProfile);
        setIsEditing(false);
        alert('Cập nhật thông tin thành công!');
      } else {
        alert('Có lỗi xảy ra khi cập nhật thông tin!');
      }
    } catch (error) {
      console.error('Error updating profile:', error);
      alert('Có lỗi xảy ra khi cập nhật thông tin!');
    }
  };

  const handleCancel = () => {
    setIsEditing(false);
    setEditedProfile({});
  };

  const handleChange = (e) => {
    setEditedProfile({
      ...editedProfile,
      [e.target.name]: e.target.value
    });
  };

  const handleAvatarChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setEditedProfile({
          ...editedProfile,
          avatar: reader.result
        });
      };
      reader.readAsDataURL(file);
    }
  };

  if (loading) {
    return (
      <div className="loading-spinner">
        <div className="spinner"></div>
        <p>Đang tải thông tin...</p>
      </div>
    );
  }

  return (
    <div className="profile-container">
      <div className="profile-header">
        <h1>Hồ Sơ Cá Nhân</h1>
        <p className="text-muted">Quản lý thông tin cá nhân của bạn</p>
      </div>

      <div className="profile-content">
        {/* Sidebar với avatar và thống kê */}
        <div className="profile-sidebar">
          <div className="avatar-section">
            <div className="avatar-wrapper">
              <img 
                src={isEditing ? editedProfile.avatar : profile.avatar} 
                alt="Avatar" 
                className="profile-avatar" 
              />
              {isEditing && (
                <div className="avatar-overlay">
                  <label htmlFor="avatar-upload" className="avatar-upload-label">
                    <i className="fas fa-camera"></i>
                  </label>
                  <input
                    type="file"
                    id="avatar-upload"
                    accept="image/*"
                    onChange={handleAvatarChange}
                    className="avatar-upload-input"
                  />
                </div>
              )}
            </div>
            <h3 className="profile-name">{profile.username}</h3>
            <div className="membership-badge">
              <i className="fas fa-crown"></i>
              <span>{profile.membershipLevel}</span>
            </div>
            <div className="points-info">
              <i className="fas fa-star"></i>
              <span>{profile.points} điểm</span>
            </div>
          </div>

          <div className="profile-stats">
            <div className="stat-item">
              <i className="fas fa-shopping-bag"></i>
              <div className="stat-details">
                <span className="stat-value">{profile.totalOrders}</span>
                <span className="stat-label">Đơn hàng</span>
              </div>
            </div>
            <div className="stat-item">
              <i className="fas fa-heart"></i>
              <div className="stat-details">
                <span className="stat-value">{profile.favoriteCount}</span>
                <span className="stat-label">Yêu thích</span>
              </div>
            </div>
            <div className="stat-item">
              <i className="fas fa-calendar-alt"></i>
              <div className="stat-details">
                <span className="stat-value">
                  {new Date(profile.joinDate).toLocaleDateString('vi-VN')}
                </span>
                <span className="stat-label">Ngày tham gia</span>
              </div>
            </div>
          </div>
        </div>

        {/* Main content với form thông tin */}
        <div className="profile-main">
          <div className="profile-form">
            <div className="form-group">
              <label>Họ và tên</label>
              {isEditing ? (
                <input
                  type="text"
                  name="fullName"
                  value={editedProfile.fullName || ''}
                  onChange={handleChange}
                  className="form-control"
                  placeholder="Nhập họ và tên"
                />
              ) : (
                <p className="form-control-static">{profile.fullName || 'Chưa cập nhật'}</p>
              )}
            </div>

            <div className="form-group">
              <label>Email</label>
              <p className="form-control-static">{profile.email}</p>
            </div>

            <div className="form-group">
              <label>Số điện thoại</label>
              {isEditing ? (
                <input
                  type="tel"
                  name="phone"
                  value={editedProfile.phone || ''}
                  onChange={handleChange}
                  className="form-control"
                  placeholder="Nhập số điện thoại"
                />
              ) : (
                <p className="form-control-static">{profile.phone || 'Chưa cập nhật'}</p>
              )}
            </div>

            <div className="form-group">
              <label>Địa chỉ</label>
              {isEditing ? (
                <textarea
                  name="address"
                  value={editedProfile.address || ''}
                  onChange={handleChange}
                  className="form-control"
                  rows="3"
                  placeholder="Nhập địa chỉ"
                />
              ) : (
                <p className="form-control-static">{profile.address || 'Chưa cập nhật'}</p>
              )}
            </div>

            <div className="profile-actions">
              {isEditing ? (
                <>
                  <button className="btn btn-primary" onClick={handleSave}>
                    <i className="fas fa-save"></i> Lưu thay đổi
                  </button>
                  <button className="btn btn-secondary" onClick={handleCancel}>
                    <i className="fas fa-times"></i> Hủy
                  </button>
                </>
              ) : (
                <button className="btn btn-primary" onClick={handleEdit}>
                  <i className="fas fa-edit"></i> Chỉnh sửa
                </button>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Profile;
