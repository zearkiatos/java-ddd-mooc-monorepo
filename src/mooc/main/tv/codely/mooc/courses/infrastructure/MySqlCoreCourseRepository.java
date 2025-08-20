package src.mooc.main.tv.codely.mooc.courses.infrastructure;

import java.util.HashMap;
import java.util.Optional;

import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.ServiceInjectable;

import javax.sql.DataSource;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseDuration;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;

import java.sql.*;

@ServiceInjectable
public class MySqlCoreCourseRepository implements CourseRepository {
     private final DataSource dataSource;

     public MySqlCoreCourseRepository(DataSource dataSource) {
         this.dataSource = dataSource;
     }

    @Override
    public void save(Course course) {
        String sql = "INSERT INTO courses (id, name, duration) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.id().toString());
            stmt.setString(2, course.name().toString());
            stmt.setString(3, course.duration().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Course> search(CourseId id) {
        String sql = "SELECT id, name, duration FROM courses WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Map ResultSet to Course
                return Optional.of(new Course(
                    new CourseId(rs.getString("id")),
                    new CourseName(rs.getString("name")),
                    new CourseDuration(rs.getString("duration"))
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
