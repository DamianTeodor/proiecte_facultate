#include "Camera.hpp"

namespace gps {

    //Camera constructor
    Camera::Camera(glm::vec3 cameraPosition, glm::vec3 cameraTarget, glm::vec3 cameraUp) {
        this->cameraPosition = cameraPosition;
        this->cameraTarget = cameraTarget;
        this->cameraDirection = glm::normalize(cameraTarget - cameraPosition);
        this->cameraRightLeftDirection = glm::normalize(glm::cross(this->cameraDirection, glm::vec3(0.0f, 1.0f, 0.0f)));
        this->cameraUpDirection = cameraUp;
        this->cameraFront = glm::normalize(glm::cross(this->cameraUpDirection, this->cameraRightLeftDirection));

        //TODO - Update the rest of camera parameters

    }

    //return the view matrix, using the glm::lookAt() function
    glm::mat4 Camera::getViewMatrix() {
        return glm::lookAt(cameraPosition, cameraPosition + cameraDirection, cameraUpDirection);
    }

    //update the camera internal parameters following a camera move event
    void Camera::move(MOVE_DIRECTION direction, float speed) {
        //TODO
        switch (direction) {
        case MOVE_FORWARD:
            if (cameraPosition.y > 0.1f) {
                cameraPosition += cameraDirection * speed;
            }
            else {
                cameraPosition.x += cameraDirection.x * speed;
                cameraPosition.z += cameraDirection.z * speed;
                if (glm::degrees(glm::acos(glm::dot(cameraDirection, glm::vec3(0.0f, 1.0f, 0.0f)))) < 90) {
                    cameraPosition.y = 0.11f;
                }
            }
            break;
        case MOVE_BACKWARD:
            if (cameraPosition.y > 0.1f) {
                cameraPosition -= cameraDirection * speed;
            }
            else {
                cameraPosition.x -= cameraDirection.x * speed;
                cameraPosition.z -= cameraDirection.z * speed;
                if (glm::degrees(glm::acos(glm::dot(cameraDirection, glm::vec3(0.0f, 1.0f, 0.0f)))) > 90) {
                    cameraPosition.y = 0.11f;
                }
            }
            break;
        case MOVE_LEFT:
            cameraPosition -= cameraRightLeftDirection * speed;
            break;
        case MOVE_RIGHT:
            cameraPosition += cameraRightLeftDirection * speed;
            break;
        default:
            break;
        }
    }

    //update the camera internal parameters following a camera rotate event
    //yaw - camera rotation around the y axis
    //pitch - camera rotation around the x axis
    void Camera::rotate(float pitch, float yaw) {
        //TODO
        glm::vec3 direction;
        direction.x = cos(glm::radians(yaw)) * cos(glm::radians(pitch));
        direction.y = sin(glm::radians(pitch));
        direction.z = sin(glm::radians(yaw)) * cos(glm::radians(pitch));
        cameraDirection = glm::normalize(direction);
        cameraRightLeftDirection = glm::normalize(glm::cross(cameraDirection, glm::vec3(0.0f, 1.0f, 0.0f)));
        this->cameraFront = glm::normalize(glm::cross(this->cameraUpDirection, this->cameraRightLeftDirection));
    }
    glm::vec3 Camera::getCameraTarget()
    {
        return this->cameraTarget;
    }
}