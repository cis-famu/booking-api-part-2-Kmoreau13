package edu.famu.hotelprodject.Util;

public record ApiResponse(boolean success, String message, Object data, Object error) {
}
